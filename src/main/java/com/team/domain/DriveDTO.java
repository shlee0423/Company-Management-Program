package com.team.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


import java.time.LocalDateTime;
import java.util.Base64;


@Getter
@Setter
@ToString
@Log4j2
public class DriveDTO {
    private String fileName;
    private LocalDateTime uploadDate;
    private String dept;
    private String url;

    private byte[] data;

    public void setData(byte[] data){
        this.data = data;
        try{
            this.url = Base64.getEncoder().encodeToString(data);
        }
        catch (Exception e){
            log.error(e);
        }
    }
}
