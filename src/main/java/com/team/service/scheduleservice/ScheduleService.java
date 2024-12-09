package com.team.service.scheduleservice;

import com.team.domain.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> selectSchedules(String employeeId);
    void insertCalendar(ScheduleDTO scheduleDTO);
    ScheduleDTO selectScheduleByNo(Integer no);
    void updateSchedule(ScheduleDTO schedule);

    void deleteSchedule(Integer no);
}
