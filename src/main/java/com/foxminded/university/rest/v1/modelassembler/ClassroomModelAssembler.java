package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.ClassroomController;
import com.foxminded.university.rest.v1.dto.ClassroomDTO;

@Component
public class ClassroomModelAssembler implements RepresentationModelAssembler<ClassroomDTO, EntityModel<ClassroomDTO>> {
    @Override
    public EntityModel<ClassroomDTO> toModel(ClassroomDTO classroom) {
        return EntityModel.of(classroom, //
                linkTo(methodOn(ClassroomController.class).getById(classroom.getId())).withSelfRel());
    }
}
