package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.CourseController;
import com.foxminded.university.rest.v1.controller.LessonController;
import com.foxminded.university.rest.v1.dto.LessonDTO;

@Component
public class LessonModelAssembler implements RepresentationModelAssembler<LessonDTO, EntityModel<LessonDTO>> {
    @Override
    public EntityModel<LessonDTO> toModel(LessonDTO lesson) {
        //lesson.getCourse()
              //.add(linkTo(methodOn(CourseController.class).getById(lesson.getCourse().getId())).withSelfRel());
        return EntityModel.of(lesson,
                   linkTo(methodOn(LessonController.class).getById(lesson.getId())).withSelfRel());
    }
}
