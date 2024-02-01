package com.school.controller;

import com.school.payload.SchoolDto;
import com.school.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService){
        this.schoolService=schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolDto> createDepartment(@RequestBody SchoolDto schoolDto){
       SchoolDto dto = schoolService.createDepartment(schoolDto);
       return  new ResponseEntity<>(dto, HttpStatus.OK);
    }

//    http://localhost:8080/api/school/particular?id=1
    @GetMapping("/particular/{id}")
    public ResponseEntity<SchoolDto> getDepartmentById(@PathVariable long id){
        SchoolDto dto = schoolService.getDepartmentById(id);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }


//    http://localhost:8080/api/school/all
    @GetMapping("/all")
    public ResponseEntity<List<SchoolDto>> getAllDepartment(){
    List<SchoolDto> dto = schoolService.getAllDepartment();
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }

//    http://localhost:8080/api/school?pageNo=0&pageSize=2&sortBy=section&sortDir=asc
    @GetMapping
    public ResponseEntity<List<SchoolDto>> getALlDepartmentPage(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "2",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "id",required = false) String sortDir
    ){
        List<SchoolDto> schoolDtos = schoolService.getALlDepartmentPage(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(schoolDtos,HttpStatus.OK);
    }

//    http://localhost:8080/api/school?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteDepartment(@RequestParam long id){
        schoolService.deleteDepartment(id);
        return new ResponseEntity<>("Deleted!!",HttpStatus.OK);
    }


//    http://localhost:8080/api/school?id=1
    @PutMapping
    public ResponseEntity<SchoolDto> updateDepartment(@RequestParam long id,@RequestBody SchoolDto schoolDto){
            SchoolDto dto = schoolService.updateDepartment(id,schoolDto);
            return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }

}
