package com.team.controller;

import com.team.domain.EmployeeDTO;
import com.team.domain.ScheduleDTO;
import com.team.service.scheduleservice.ScheduleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/calendar")
    public void getCalender(
//            @PathVariable("employee") String employeeId,
            @AuthenticationPrincipal EmployeeDTO employee,
            Model model
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("employee",employee);
    }

    @ResponseBody
    @GetMapping("/{employeeId}")
    public List<ScheduleDTO> getSchedules(
            @PathVariable("employeeId") String employeeId
    ){
        List<ScheduleDTO> schedules = scheduleService.select_schedules(employeeId);
        return schedules;
    }

    @PostMapping("/calendar")
    public ResponseEntity<ScheduleDTO> postInsertSchedule(
            @RequestBody ScheduleDTO schedule){
        scheduleService.insert_calendar(schedule);
        log.info(schedule.toString());
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/calendar/{no}")
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable("no") Integer no){
        ScheduleDTO schedule = scheduleService.select_scheduleByNo(no);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/{no}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable("no") Integer no,
            @RequestBody ScheduleDTO schedule
    ){
        schedule.setNo(no);
        System.out.println("업데이트: " + schedule);
        scheduleService.update_schedule(schedule);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable("no") Integer no
    ){
        scheduleService.delete_schedule(no);
        return ResponseEntity.ok().body(null);
    }

}
