package com.team.converter;

import com.team.domain.FileDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileConverter implements Converter<MultipartFile, FileDTO> {
    @Override
    public FileDTO convert(MultipartFile file) {
        System.out.println("FileConverter 실행...");
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String saveFileName = uuid + "_" + fileName;
        if(Objects.isNull(fileName)) {
            System.out.println("파일 없음");
            return null;
        }
        byte[] data = null;
        try{
            data = file.getBytes();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return FileDTO.builder()
                .UUID(uuid)
                .originalFileName(fileName)
                .saveFileName(saveFileName)
                .data(data)
                .build();
    }
}