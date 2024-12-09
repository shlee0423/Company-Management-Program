package com.team.mapper;

import com.team.domain.ImageDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FileMapper {
    @Insert("Insert INTO images (name,data) values (#{name}, #{data})")
    void insertFile(ImageDTO imageDTO);

    @Select("SELECT * from images limit 1;")
    ImageDTO selectImage();
}
