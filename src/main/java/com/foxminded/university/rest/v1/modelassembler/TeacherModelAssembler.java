package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.CourseController;
import com.foxminded.university.rest.v1.controller.TeacherController;
import com.foxminded.university.rest.v1.dto.TeacherDTO;

@Component
public class TeacherModelAssembler implements RepresentationModelAssembler<TeacherDTO, EntityModel<TeacherDTO>> {
    @Override
    public EntityModel<TeacherDTO> toModel(TeacherDTO teacher) {
        teacher.getCourses()
               .forEach(c -> c.add(linkTo(methodOn(CourseController.class).getById(c.getId())).withSelfRel()));
        return EntityModel.of(teacher, 
                linkTo(methodOn(TeacherController.class).getById(teacher.getId())).withSelfRel(),
                linkTo(methodOn(TeacherController.class).getAll()).withRel("teacher"));
    }
}
