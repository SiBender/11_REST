package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.CourseController;
import com.foxminded.university.rest.v1.controller.TeacherController;
import com.foxminded.university.rest.v1.dto.CourseDTO;

@Component
public class CourseModelAssembler implements RepresentationModelAssembler<CourseDTO, EntityModel<CourseDTO>> {
    @Override
    public EntityModel<CourseDTO> toModel(CourseDTO course) {
        //course.getTeacher()
        //      .add(linkTo(methodOn(TeacherController.class).getById(course.getTeacher().getId())).withSelfRel());
        return EntityModel.of(course, 
                   linkTo(methodOn(CourseController.class).getById(course.getId())).withSelfRel());
    }

}
