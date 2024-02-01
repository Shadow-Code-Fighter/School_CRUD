package com.school.service.serviceImpl;

import com.school.entity.School;
import com.school.exception.ResourceNotFoundException;
import com.school.payload.SchoolDto;
import com.school.repository.SchoolRepo;
import com.school.service.SchoolService;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepo schoolRepo;
    private ModelMapper modelMapper;

    public SchoolServiceImpl(SchoolRepo schoolRepo,ModelMapper modelMapper){
        this.schoolRepo=schoolRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public SchoolDto createDepartment(SchoolDto schoolDto) {
        School school = modelMapper.map(schoolDto, School.class);
        School saveSchool = schoolRepo.save(school);
        SchoolDto dto = modelMapper.map(saveSchool, SchoolDto.class);
        return dto;
    }

    @Override
    public SchoolDto getDepartmentById(long id) {
        School school = schoolRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Department not found with id: "+id)
        );
        SchoolDto dto = modelMapper.map(school, SchoolDto.class);
        return dto;
    }

    @Override
    public List<SchoolDto> getAllDepartment() {
        List<School> school = schoolRepo.findAll();
        List<SchoolDto> dto = school.stream().map(s -> modelMapper.map(s, SchoolDto.class)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public List<SchoolDto> getALlDepartmentPage(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageale = PageRequest.of(pageNo, pageSize, sort);
        Page<School> pageSchool = schoolRepo.findAll(pageale);
        List<School> school = pageSchool.getContent();
        List<SchoolDto> dto = school.stream().map(s -> modelMapper.map(s, SchoolDto.class)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public void deleteDepartment(long id) {
        schoolRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("School not found with id:" + id));
        schoolRepo.deleteById(id);
    }

    @Override
    public SchoolDto updateDepartment(long id, SchoolDto schoolDto) {
        School school = schoolRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department not found with id: " + id)
        );
        school.setClassRoom(schoolDto.getClassRoom());
        school.setSection(schoolDto.getSection());
        school.setDescription(schoolDto.getDescription());
        school.setStudents(schoolDto.getStudents());
        School saveSchool = schoolRepo.save(school);
        SchoolDto dto = modelMapper.map(saveSchool, SchoolDto.class);
        return dto;
    }


}
