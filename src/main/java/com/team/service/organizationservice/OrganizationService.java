package com.team.service.organizationservice;

import com.team.domain.organize.DepartDTO;
import com.team.domain.organize.DepartDetailDTO;
import com.team.domain.organize.RepresentDTO;

import java.util.List;

public interface OrganizationService {
    RepresentDTO selectRepresent();
    List<DepartDTO> selectDepart();
    List<DepartDetailDTO> selectDepartDetail();

    void updateRepresentContent(String representContent);
    void deleteDepartDetail();
    void insertDepartDetail(Integer departNo, String detailName);
    void updateDepartData(Integer departNo, String departName);
}
