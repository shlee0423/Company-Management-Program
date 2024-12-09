package com.team.mapper;

import com.team.domain.ProductDTO;
import com.team.domain.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    // 모든 일정 불러오기
    List<ScheduleDTO> selectSchedules(String employeeId);
    // 일정 추가
    void insertCalendar(ScheduleDTO scheduleDTO);
    // no로 일정 찾기
    ScheduleDTO selectScheduleByNo(Integer no);

    void updateSchedule(ScheduleDTO scheduleDTO);

    void deleteSchedule(Integer no);
}
