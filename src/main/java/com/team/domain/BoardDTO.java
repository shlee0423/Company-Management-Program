package com.team.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private Integer boardNo;
    private String boardTitle;
    private EmployeeDTO boardWriter;
    private String boardDate;
    private String boardContent;
    private String boardCategory;
    private Integer boardViewCount;
    private String boardImage;
}
