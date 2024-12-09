package com.team.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.Base64;

@Getter
@Setter
@Builder
@ToString(exclude = {"data","url"})
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class FileDTO {
    private String UUID;
    private String originalFileName;
    private String saveFileName;
    private byte[] data;
    private String url;


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
