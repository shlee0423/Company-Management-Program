package com.team.service.auth;

import com.team.domain.EmployeeDTO;
import com.team.mapper.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
public class DetailService implements UserDetailsService {
    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("아이디 : " + username);

        // 데이터베이스에서 사용자 정보 조회
        EmployeeDTO employee = mapper.selectEmployeeById(username);

        System.out.println("employee 조회 : " + employee);
        // 사용자가 존재하지 않으면 예외 던짐
        if (employee == null) {
            throw new UsernameNotFoundException("유저가 없음: " + username);
        }

        // 비밀번호 암호화
        String password = passwordEncoder.encode("1234");
        System.out.println("로그인하는 비밀번호 :" + password);

        return employee;

    }
}
