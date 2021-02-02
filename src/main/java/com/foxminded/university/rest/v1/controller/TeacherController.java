package com.foxminded.university.rest.v1.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.controller.service.AdministrativeService;
import com.foxminded.university.controller.service.TeachersService;
import com.foxminded.university.rest.v1.dto.TeacherDTO;
import com.foxminded.university.rest.v1.dtoconvert.TeacherDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.TeacherModelAssembler;

@RestController
public class TeacherController {
    private AdministrativeService administrativeService;
    private TeachersService teachersService;
    private TeacherModelAssembler teacherAssembler;
    private TeacherDTOConverter teacherConverter;
    
    @Autowired 
    public TeacherController(AdministrativeService administrativeService, TeachersService teachersService,
                             TeacherModelAssembler teacherAssembler, TeacherDTOConverter teacherConverter) {
        this.administrativeService = administrativeService;
        this.teachersService = teachersService;
        this.teacherAssembler = teacherAssembler;
        this.teacherConverter = teacherConverter;
    }
    
    @GetMapping("/restapi/v1/teacher")
    public CollectionModel<EntityModel<TeacherDTO>> getAll() {     
        List<EntityModel<TeacherDTO>> teachers = teachersService.getAll()
                                                .stream()
                                                .map(teacherConverter::toDTO)
                                                .map(teacherAssembler::toModel)
                                                .collect(Collectors.toList());
        return CollectionModel.of(teachers, 
                linkTo(methodOn(TeacherController.class).getAll()).withSelfRel());
    }
    
    @GetMapping("/restapi/v1/teacher/{id}")
    public EntityModel<TeacherDTO> getById(@PathVariable Integer id) {
        return teacherAssembler.toModel(teacherConverter.toDTO(administrativeService.getTeacherById(id)));
    }
    
    @PostMapping("/restapi/v1/teacher")
    public void addTeacher(@RequestBody TeacherDTO teacher) {
        administrativeService.createTeacher(teacher.getFirstName(), teacher.getLastName(), teacher.getFaculty().getId());
    }
    
    @PutMapping("/restapi/v1/teacher")
    public void updateTeacher(@RequestBody TeacherDTO teacher) {
        administrativeService.updateTeacher(teacherConverter.toModel(teacher));
    }
    
    @DeleteMapping("/restapi/v1/teacher")
    public void deleteTeacher(@RequestBody TeacherDTO teacher) {
        administrativeService.deleteTeacher(teacher.getId());
    }
    
    @DeleteMapping("/restapi/v1/teacher/{id}")
    public void deleteTeacherById(@PathVariable Integer id) {
        administrativeService.deleteTeacher(id);
    }
}
