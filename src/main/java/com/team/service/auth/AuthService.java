package com.team.service.auth;

import com.team.domain.EmployeeDTO;
import com.team.mapper.Mapper;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Log4j2
@Service
public class AuthService {
    @Autowired
    private Mapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private RestOperations restOperations;

    private final String PORT_ONE_IMP_KEY = "3858436703354140";
    private String PORT_ONE_IMP_SECRET = "wmY8o1kqjh8DeC0I6256QhB0w3O1Ap3zwTLm0k6LKT8QlzL0kQi4Xt2DioS4T3LhYifI4TrusLo4INg1";
    private final String PORT_ONE_ACCESS_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String PORT_ONE_USER_CERT_INFO_URL = "https://api.iamport.kr/certifications/{impUid}";


    public boolean isEmployeeIdAvailable(String employeeId) {
        return mapper.selectEmployeeIdIsAvailable(employeeId);
    }


    private String get_portone_access_token() {
        RequestEntity<String> getAccessTokenRequest = RequestEntity
                .post(PORT_ONE_ACCESS_TOKEN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(JSONObject.toJSONString(Map.of(
                        "imp_key", PORT_ONE_IMP_KEY,
                        "imp_secret", URLEncoder.encode(PORT_ONE_IMP_SECRET, StandardCharsets.UTF_8)
                )));

        ResponseEntity<Map> getAccessTokenResponse = restOperations.exchange(getAccessTokenRequest, Map.class);
        // 요청이 성공적으로 완료되었음
        if (getAccessTokenResponse.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, Map> body = (Map<String, Map>) getAccessTokenResponse.getBody();
            Map<String, String> response = body.get("response");
            String accessToken = response.get("access_token");
            log.info("액세스토큰 발급 완료");
            return accessToken;
        }
        log.error("요청에러(실패)");
        // 요청에 실패함
        return null;
    }

    // 포트원의 본인인증 정보를 얻는 메서드 (유저의 ci값을 얻음)
    private String get_portone_user_cert_info(String impUid, String accessToken) {
        RequestEntity<Void> userCertRequest = RequestEntity.get(PORT_ONE_USER_CERT_INFO_URL, impUid)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        ResponseEntity<Map> userCertResponse = restOperations.exchange(userCertRequest, Map.class);
        // 200. OK. 요청이 성공하였음
        if (userCertResponse.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, Object> body = userCertResponse.getBody();
            Map<String, Object> response = (Map<String, Object>) body.get("response");
            Boolean certified = (Boolean) response.get("certified");
            if (certified) {
                log.info("인증성공");
                String uniqueKey = response.get("unique_key").toString();
                return uniqueKey;
            }
            log.warn("인증실패");
            return null;
        }
        log.error("요청에러(실패)");
        return null;
    }


    // 유저 생성 (회원가입)
    public boolean insertEmployee(String impUid, EmployeeDTO employee) {
        if(impUid.isBlank()){
            log.info(impUid);
            return false;
        }
        String accessToken = get_portone_access_token();
        log.info("액세스 토큰" + accessToken);
        if(accessToken == null){
            return false;
        }
        String employeeCi = get_portone_user_cert_info(impUid, accessToken);
        log.info("ci " + employeeCi);
        if(employeeCi == null){
            return false;
        }
        // 유저를 회원가입 시킬 때, CI 값을 같이 넣어준다
        employee.setEmployeeCi(employeeCi);
        log.info("user get ci" + employee.getEmployeeCi());
        // 유저를 회원가입 시킬 때, 패스워드를 인코딩해서 넣는다
        employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
        log.info(employee.getEmployeePassword());

        // 유저를 insert한다
        EmployeeDTO result = mapper.insertEmployee(employee);
        System.out.println(result);
        mapper.insertEmployeeFile(result);
        return true;
    }
}
