package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.FacultyController;
import com.foxminded.university.rest.v1.dto.FacultyDTO;

@Component
public class FacultyModelAssembler implements RepresentationModelAssembler<FacultyDTO, EntityModel<FacultyDTO>> {
    @Override
    public EntityModel<FacultyDTO> toModel(FacultyDTO faculty) {
        return EntityModel.of(faculty, //
                linkTo(methodOn(FacultyController.class).getById(faculty.getId())).withSelfRel(),
                linkTo(methodOn(FacultyController.class).getAll()).withRel("faculty"));
    }
}
