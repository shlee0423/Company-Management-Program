package com.team.service.menuservice;

import com.team.domain.EmployeeDTO;
import com.team.mapper.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private Mapper mapper;

    @Override
    public EmployeeDTO selectEmployeeById(String id) {
         return mapper.selectEmployeeById(id);
    }
}
