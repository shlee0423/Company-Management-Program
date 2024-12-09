package com.team.service.scheduleservice;

import com.team.domain.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> select_schedules(String employeeId);
    void insert_calendar(ScheduleDTO scheduleDTO);
    ScheduleDTO select_scheduleByNo(Integer no);
    void update_schedule(ScheduleDTO schedule);

    void delete_schedule(Integer no);
}
