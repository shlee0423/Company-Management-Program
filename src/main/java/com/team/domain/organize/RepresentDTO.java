package com.team.domain.organize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Base64;

@Getter
@Setter
@ToString
public class RepresentDTO {
    private Integer representNo;
    private String representContent;
    private byte[] representImage;
    private String url;

    public void setData(byte[] representImage) {
        this.url = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(representImage);
        this.representImage = representImage;
    }
}
