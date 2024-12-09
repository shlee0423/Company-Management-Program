package com.team.service.scheduleservice;

import com.team.domain.ScheduleDTO;
import com.team.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired private ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleDTO> selectSchedules(
            String employeeId
    ) {
        return scheduleMapper.selectSchedules(employeeId);
    }

    @Override
    public void insertCalendar(ScheduleDTO scheduleDTO) {
        scheduleMapper.insertCalendar(scheduleDTO);
    }

    @Override
    public ScheduleDTO selectScheduleByNo(Integer no) {
        return scheduleMapper.selectScheduleByNo(no);
    }

    @Override
    public void updateSchedule(ScheduleDTO schedule) {
        scheduleMapper.updateSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Integer no) {
        scheduleMapper.deleteSchedule(no);
    }
}
