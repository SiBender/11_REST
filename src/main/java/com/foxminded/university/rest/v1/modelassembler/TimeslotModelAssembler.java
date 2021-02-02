package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.TimeslotController;
import com.foxminded.university.rest.v1.dto.TimeslotDTO;

@Component
public class TimeslotModelAssembler implements RepresentationModelAssembler<TimeslotDTO, EntityModel<TimeslotDTO>> {
    @Override
    public EntityModel<TimeslotDTO> toModel(TimeslotDTO timeslot) {
        return EntityModel.of(timeslot, 
                linkTo(methodOn(TimeslotController.class).getById(timeslot.getId())).withSelfRel(),
                linkTo(methodOn(TimeslotController.class).getAll()).withRel("timeslot"));
    }
}
