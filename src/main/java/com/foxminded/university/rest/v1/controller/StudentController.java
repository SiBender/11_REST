package com.foxminded.university.rest.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.controller.service.StudentsService;
import com.foxminded.university.rest.v1.dto.StudentDTO;
import com.foxminded.university.rest.v1.dtoconvert.StudentDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.StudentModelAssembler;

@RestController
public class StudentController {
    private StudentsService studentsService;
    private StudentModelAssembler studentAssembler;
    private StudentDTOConverter studentConverter;

    @Autowired
    public StudentController(StudentsService studentsService, StudentModelAssembler studentAssembler, 
                             StudentDTOConverter studentConverter) {
        this.studentsService = studentsService;
        this.studentAssembler = studentAssembler;
        this.studentConverter = studentConverter;
    }
    
    @GetMapping("/restapi/v1/student/{id}")
    public EntityModel<StudentDTO> getById(@PathVariable Integer id) {
        return studentAssembler.toModel(studentConverter.toDTO(studentsService.getStudent(id)));
    }
    
    @PostMapping("/restapi/v1/student")
    public void addStudent(@RequestBody StudentDTO student) {
        studentsService.addStudent(student.getFirstName(), student.getLastName(), student.getGroup().getId());
    }
    
    @PutMapping("/restapi/v1/student")
    public void updateStudent(@RequestBody StudentDTO student) {
        studentsService.update(studentConverter.toModel(student));
    }
    
    @DeleteMapping("/restapi/v1/student/{id}")
    public void deleteStudentById(@PathVariable Integer id) {
        studentsService.deleteStudent(id);
    }
}
