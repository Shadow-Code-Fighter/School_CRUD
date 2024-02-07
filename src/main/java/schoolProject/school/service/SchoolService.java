package schoolProject.school.service;

import schoolProject.school.payload.SchoolDto;

import java.util.List;

public interface SchoolService {

    SchoolDto createDepartment(SchoolDto schoolDto);

    SchoolDto getDepartmentById(long id);

    List<SchoolDto> getAllDepartment();


    List<SchoolDto> getALlDepartmentPage(int pageNo, int pageSize, String sortBy, String sortDir);


    void deleteDepartment(long id);

    SchoolDto updateDepartment(long id, SchoolDto schoolDto);
}
