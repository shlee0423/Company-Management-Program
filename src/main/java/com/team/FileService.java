package com.team;

import com.team.domain.ImageDTO;
import com.team.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public void saveFile(String name, byte[] data){
        ImageDTO Image = new ImageDTO();
        Image.setName(name);
        Image.setData(data);

        fileMapper.insertFile(Image);

    }

    public ImageDTO getFile(){
        return fileMapper.selectImage();
    }
}
