package com.team.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Base64;

@Getter
@Setter
@ToString
public class ImageDTO {
    private int id;
    private String name;
    private byte[] data;
    private String url;

    public void setData(byte[] data) {
        this.url = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(data);
        this.data = data;
    }
}
