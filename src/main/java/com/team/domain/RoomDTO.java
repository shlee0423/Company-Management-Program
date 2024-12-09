package com.team.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RoomDTO {
    private Integer roomNo;
    private List<EmployeeDTO> userId; // 채팅방 인원들
    private List<ChatDTO> chats; // 채팅 메세지들
}
