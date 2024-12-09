package com.team.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class NoticeDTO {
    private Integer boardNo;
    private String boardCategory;
    private String boardTitle;
    private String boardContent;
    private String boardImage;
    private String boardWriter;
    private LocalDate boardDate;
    private Integer boardViewCount;

}