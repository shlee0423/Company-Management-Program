package com.team.controller.menu;

import com.team.FileService;
import com.team.domain.EmployeeDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@Log4j2
public class DriveController {
    @Autowired
    private final FileService fileService;

    public DriveController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/menu/drive")
    public String get_drive(@AuthenticationPrincipal EmployeeDTO employeeDTO, Model model) {
        if(!Objects.isNull(employeeDTO)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            model.addAttribute("employee", employeeDTO);
            model.addAttribute("name", name);
            return "/menu/drive";
        }
        return null;
    }

//    @PostMapping("/menu/drive")
//    public void post_drive(EmployeeDTO employeeDTO){
//        try {
//            employeeDTO.setEmployeeId("test");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("hi" + employeeDTO);
//        fileService.insertFilesService(employeeDTO);
//    }
}
