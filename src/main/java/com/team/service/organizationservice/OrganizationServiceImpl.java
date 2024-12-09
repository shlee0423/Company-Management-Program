package com.team.service.organizationservice;


import com.team.domain.organize.DepartDTO;
import com.team.domain.organize.DepartDetailDTO;
import com.team.domain.organize.RepresentDTO;
import com.team.mapper.OrganizationMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public RepresentDTO selectRepresent() {
        return organizationMapper.selectRepresent();
    }

    @Override
    public List<DepartDTO> selectDepart() {
        return organizationMapper.selectDepart();
    }

    @Override
    public List<DepartDetailDTO> selectDepartDetail() {
        return organizationMapper.selectDepartDetail();
    }

    @Override
    public void updateRepresentContent(String representContent) {
        organizationMapper.UpdateRepresentContent(representContent);
    }

    @Override
    public void deleteDepartDetail() {
        organizationMapper.DeleteDepartDetail();
    }

    @Override
    public void updateDepartData(Integer departNo, String departName) {
        organizationMapper.UpdateDepartData(departNo, departName);
    }

    @Override
    public void insertDepartDetail(Integer departNo, String detailName) {
        organizationMapper.InsertDepartDetail(departNo, detailName);
    }

}
