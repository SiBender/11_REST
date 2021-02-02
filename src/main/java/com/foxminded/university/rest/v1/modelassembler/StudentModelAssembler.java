package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.StudentController;
import com.foxminded.university.rest.v1.dto.StudentDTO;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<StudentDTO, EntityModel<StudentDTO>> {
    @Override
    public EntityModel<StudentDTO> toModel(StudentDTO student) {
        return EntityModel.of(student, 
                linkTo(methodOn(StudentController.class).getById(student.getId())).withSelfRel());
    }
}
