package com.foxminded.university.rest.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.controller.service.TeachersService;
import com.foxminded.university.model.Course;
import com.foxminded.university.rest.v1.dto.CourseDTO;
import com.foxminded.university.rest.v1.dtoconvert.CourseDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.CourseModelAssembler;

@RestController
public class CourseController {
    private TeachersService teachersService;
    private CourseModelAssembler courseAssembler;
    private CourseDTOConverter courseConverter;
    
    @Autowired
    public CourseController(TeachersService teachersService, CourseModelAssembler courseAssembler,
                            CourseDTOConverter courseConverter) {
        this.teachersService = teachersService;
        this.courseAssembler = courseAssembler;
        this.courseConverter = courseConverter;
    }
    
    @GetMapping("/restapi/v1/course/{id}")
    public EntityModel<CourseDTO> getById(@PathVariable Integer id) {
        return courseAssembler.toModel(courseConverter.toDTO(teachersService.getCourse(id)));
    }
    
    @PostMapping("/restapi/v1/course/")
    public void addCourse(@RequestBody CourseDTO courseDTO) {
        teachersService.createCourse(courseDTO.getName(), courseDTO.getDescription(), courseDTO.getTeacher().getId());
    } 

    @PutMapping("/restapi/v1/course/")
    public void updateCourse(@RequestBody CourseDTO courseDTO) {
        Course course = courseConverter.toModel(courseDTO);
        teachersService.updateCourse(course);
    }
}
