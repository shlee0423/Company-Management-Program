package com.team.mapper;

import com.team.domain.ChatDTO;
import com.team.domain.EmployeeDTO;
import com.team.domain.RoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
    EmployeeDTO selectEmployeeById(String employeeId);
    List<EmployeeDTO> selectAllWithoutMe(String employeeId);

    List<RoomDTO> selectRooms(String employeeId);

    void insertUserInRoom(
            @Param("roomNo") Integer roomNo,
            @Param("employeeId") String employeeId
    );

    void insertChat(
            @Param("roomNo") Integer roomNo,
            @Param("chatDTO") ChatDTO chatDTO);
}
