package com.team.controller;

import com.team.domain.EmployeeDTO;
import com.team.domain.ReservationDTO;
import com.team.service.reserveservice.ReserveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
@Log4j2
public class ReservationController {
    @Autowired
    ReserveService reserveService;

    @GetMapping("/reservation")
    public String get_reservation(
            @RequestParam("productNo") Integer productNo
            ) {

        return "reservation/reservation";
    }

    @PostMapping("/reservation")
    public String post_reservation(
            @ModelAttribute @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            ReservationDTO reservationDTO
    ) {
        reserveService.insertReservation(reservationDTO);
        log.info(reservationDTO);
        return "redirect:/product/manage_product";
    }

    @GetMapping("/list")
    public String get_list(Model model, String query,
                           @AuthenticationPrincipal EmployeeDTO employee
                           ){
        List<ReservationDTO> reservationList = reserveService.selectReservations(query);

        System.out.println(reservationList);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name", name);
        model.addAttribute("employee",employee);
        model.addAttribute("reservationList", reservationList);
        return "/reservation/reservation_list";
    }

    @GetMapping("/check/{reservationNo}")
    public String get_check(
            @PathVariable("reservationNo") Integer reservationNo,
            Model model
    ) {

        ReservationDTO reservation = reserveService.select_reservation_by_no(reservationNo);
        model.addAttribute("reservation", reservation);

        return "/reservation/reservation_check";
    }

    @PostMapping("/list")
    public ResponseEntity<String> post_reservation_update(
            @RequestBody Integer item

    ){
        reserveService.reservationApprove(item);
        return ResponseEntity.ok("승인되었습니다");
    }

    @DeleteMapping("/list")
    public ResponseEntity<String> post_reservation_delete(
            @RequestBody Integer item

    ){
        reserveService.deleteReservation(item);
        return ResponseEntity.ok("거부되었습니다");
    }

}
