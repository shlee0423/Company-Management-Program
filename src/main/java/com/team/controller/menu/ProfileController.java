package com.team.controller.menu;

import com.team.domain.EmployeeDTO;
import com.team.service.auth.AuthService;
import com.team.service.menuservice.ProfileServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/menu")
@Log4j2
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;
    // 아이디로 유저 데이터 보여주기
    @GetMapping("/profile/{employeeId}")
    public String get_profile(
            @PathVariable("employeeId") String employeeId,
            Model model,
            @AuthenticationPrincipal EmployeeDTO employeeDTO
            ){

        System.out.println("아디 : " + employeeId);
        model.addAttribute("employee",employeeDTO);
        System.out.println("프로필 직원 : " + employeeDTO);

        if(!Objects.isNull(employeeDTO)){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            System.out.println("로그인한 네임" + name);

            model.addAttribute("name", name);

            EmployeeDTO profile = profileService.selectEmployeeById(employeeId);
            model.addAttribute("profile", profile);

//            String profileImageBase64 = profileService.getProfileImageBase64(employeeId);
//            model.addAttribute("profileImageBase64", profileImageBase64);

            return "/menu/profile";
        }
        return "redirect:/auth/login";
    }

//    @PostMapping("/profile/{employeeId}")
//    public String updateProfile(@ModelAttribute EmployeeDTO employeeDTO,
//                                RedirectAttributes redirectAttributes) {
//        try {
//            profileService.updateProfile(employeeDTO);
//            redirectAttributes.addFlashAttribute("message", "프로필이 성공적으로 업데이트되었습니다.");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("message", "프로필 업데이트에 실패했습니다.");
//        }
//        return "redirect:/menu/profile/${employeeId}";
//    }

}
