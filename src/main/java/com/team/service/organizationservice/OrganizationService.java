package com.team.service.organizationservice;

import com.team.domain.organize.DepartDTO;
import com.team.domain.organize.DepartDetailDTO;
import com.team.domain.organize.RepresentDTO;

import java.util.List;

public interface OrganizationService {
    RepresentDTO select_Represent();
    List<DepartDTO> select_depart();
    List<DepartDetailDTO> select_DepartDetail();

    void update_representContent(String representContent);
    void delete_departDetail();
    void insert_departDetail(Integer departNo, String detailName);
    void update_departData(Integer departNo, String departName);
}
