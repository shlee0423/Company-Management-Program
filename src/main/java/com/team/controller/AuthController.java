package com.team.controller;

import com.team.domain.EmployeeDTO;
import com.team.service.auth.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Log4j2
@Controller
// 매핑 하는 경로 앞에 auth 를 붙여줌
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String get_login(){
        return "auth/login";
    }
    @GetMapping("/find_password")
    public void get_find_password(){

    }
    @GetMapping("/register")
    public void get_register(){
    }

    @GetMapping("/register/{employeeId}")
    public ResponseEntity<Boolean> checkAvailableId(@PathVariable("employeeId") String employeeId){
        boolean isAvailable = authService.isEmployeeIdAvailable(employeeId);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/register")
    // html impUid 가져오는 RequestParam 요소의 name 으로 가져옴
    public String post_employee_register(
            @RequestParam("impUid") String impUid,
            EmployeeDTO employeeDTO,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        log.info("impuid" + impUid);
        log.info("employeeDTO" + employeeDTO);
        boolean result = authService.insertEmployee(impUid, employeeDTO);
        if(result){
            return "redirect:/auth/login";
        }
        redirectAttributes.addFlashAttribute("certErrorMessage","본인인증안됨");
        return "redirect:/auth/register";
    }


}
