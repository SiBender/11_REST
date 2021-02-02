package com.foxminded.university.rest.v1.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.foxminded.university.rest.v1.controller.CourseController;
import com.foxminded.university.rest.v1.controller.GroupController;
import com.foxminded.university.rest.v1.controller.StudentController;
import com.foxminded.university.rest.v1.dto.GroupDTO;

@Component
public class GroupModelAssembler implements RepresentationModelAssembler<GroupDTO, EntityModel<GroupDTO>> {    
    @Override
    public EntityModel<GroupDTO> toModel(GroupDTO group) {
        //group.getCourses()
             //.forEach(c -> c.add(linkTo(methodOn(CourseController.class).getById(c.getId())).withSelfRel()));
        //group.getStudents()
             //.forEach(s -> s.add(linkTo(methodOn(StudentController.class).getById(s.getId())).withSelfRel()));
        return EntityModel.of(group,
                linkTo(methodOn(GroupController.class).getById(group.getId())).withSelfRel(),
                linkTo(methodOn(GroupController.class).getAll()).withRel("group"),
                linkTo(methodOn(GroupController.class).assignGroupsCourse(group.getId(), null)).withSelfRel(),
                linkTo(methodOn(GroupController.class).removeGroupsCourse(group.getId(), null)).withSelfRel());
    }
}
