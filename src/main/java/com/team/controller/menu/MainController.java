package com.team.controller.menu;

import com.team.domain.EmployeeDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Log4j2
@Controller
@RequestMapping("/main")

public class MainController {
    // index 페이지를 불러옴
    @GetMapping("/index")
    public String get_index(@AuthenticationPrincipal EmployeeDTO employeeDTO, Model model){
        // 메인 페이지에 유저명 표시
        if(!Objects.isNull(employeeDTO)){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            log.info("메인 페이지에서 헤더의 임플로이 " + employeeDTO);
            model.addAttribute("employee", employeeDTO);
            model.addAttribute("name", name);
            return "/main/index";
        }
        return "redirect:/auth/login";
    }

    @ResponseBody
    @PostMapping("/index")
    public ResponseEntity<Void> post_index(@AuthenticationPrincipal EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);
        if(Objects.isNull(employeeDTO)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

