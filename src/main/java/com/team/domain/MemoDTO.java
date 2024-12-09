package com.team.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class MemoDTO {
    private Integer memoNo;
    private EmployeeDTO memoWriter;
    private String memoName;
    private String memoContent;
    private LocalDateTime memoDate;
}
