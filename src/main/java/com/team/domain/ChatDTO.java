package com.team.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {
    private Integer chatNo;
    private EmployeeDTO employee; // 메세지 보낸 사람
    private String chatMessage;
    private String chatStatus;
}
