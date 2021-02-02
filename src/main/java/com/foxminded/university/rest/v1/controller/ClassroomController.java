package com.foxminded.university.rest.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

import com.foxminded.university.controller.service.LessonService;
import com.foxminded.university.model.Classroom;
import com.foxminded.university.rest.v1.dto.ClassroomDTO;
import com.foxminded.university.rest.v1.dtoconvert.ClassroomDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.ClassroomModelAssembler;

@RestController
public class ClassroomController {
    private LessonService lessonService;
    private ClassroomModelAssembler classroomAssembler;
    private ClassroomDTOConverter converter;
    
    @Autowired
    public ClassroomController(LessonService lessonService, ClassroomModelAssembler classroomAssembler,
                               ClassroomDTOConverter converter) {
        this.lessonService = lessonService;
        this.classroomAssembler = classroomAssembler;
        this.converter = converter;
    }
    
    @GetMapping("/restapi/v1/classroom")
    public CollectionModel<EntityModel<ClassroomDTO>> getAll() { 
        List<EntityModel<ClassroomDTO>> classrooms = lessonService.getAllClassrooms().stream()
                                                  .map(converter::toDTO)
                                                  .map(classroomAssembler::toModel)
                                                  .collect(Collectors.toList());
        return CollectionModel.of(classrooms, 
                                  linkTo(methodOn(ClassroomController.class).getAll()).withSelfRel());
    }
    
    @GetMapping("/restapi/v1/classroom/{id}")
    public EntityModel<ClassroomDTO> getById(@PathVariable Integer id) {
        return classroomAssembler.toModel(converter.toDTO(lessonService.getClassroomById(id)));
    }
    
    @PostMapping("/restapi/v1/classroom")
    public void addClassroom(@RequestBody ClassroomDTO classroomDTO) {
        Classroom classroom = converter.toModel(classroomDTO);
        lessonService.addClassroom(classroom);
    }
    
    @PutMapping("/restapi/v1/classroom")
    public void updateClassroom(@RequestBody ClassroomDTO classroomDTO) {
        Classroom classroom = converter.toModel(classroomDTO);
        lessonService.updateClassroom(classroom);
    }
    
    @DeleteMapping("/restapi/v1/classroom")
    public void deleteClassroom(@RequestBody ClassroomDTO classroomDTO) {
        lessonService.deleteClassroom(classroomDTO.getId());
    }
    
    @DeleteMapping("/restapi/v1/classroom/{id}")
    public void deleteClassroomById(@PathVariable Integer id) {
        lessonService.deleteClassroom(id);
    }
}
