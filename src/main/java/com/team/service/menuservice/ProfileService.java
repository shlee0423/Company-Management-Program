package com.team.service.menuservice;

import com.team.domain.EmployeeDTO;
import lombok.extern.log4j.Log4j2;


public interface ProfileService {
    EmployeeDTO selectEmployeeById(String id);
}
