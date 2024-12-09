package com.team.mapper;

import com.team.domain.organize.DepartDTO;
import com.team.domain.organize.DepartDetailDTO;
import com.team.domain.organize.RepresentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    RepresentDTO selectRepresent();
    List<DepartDTO> selectDepart();
    List<DepartDetailDTO> selectDepartDetail();

    void DeleteDepartDetail();
    void InsertDepartDetail(Integer departNo, String detailName);
    void UpdateRepresentContent(String representContent);
    void UpdateDepartData(Integer departNo, String departName);
}
