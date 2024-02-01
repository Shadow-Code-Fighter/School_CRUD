package com.school.service;

import com.school.payload.SchoolDto;

import java.util.List;
import java.util.Set;

public interface SchoolService {

    SchoolDto createDepartment(SchoolDto schoolDto);

    SchoolDto getDepartmentById(long id);

    List<SchoolDto> getAllDepartment();


    List<SchoolDto> getALlDepartmentPage(int pageNo, int pageSize, String sortBy, String sortDir);


    void deleteDepartment(long id);

    SchoolDto updateDepartment(long id, SchoolDto schoolDto);
}
