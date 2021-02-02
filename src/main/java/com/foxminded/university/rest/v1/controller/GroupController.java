package com.foxminded.university.rest.v1.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

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

import com.foxminded.university.controller.service.AdministrativeService;
import com.foxminded.university.controller.service.StudentsService;
import com.foxminded.university.controller.service.TeachersService;
import com.foxminded.university.rest.v1.dto.GroupDTO;
import com.foxminded.university.rest.v1.dtoconvert.GroupDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.GroupModelAssembler;

@RestController
public class GroupController {
    private AdministrativeService administrativeService;
    private StudentsService studentsService;
    private TeachersService teachersService;
    private GroupModelAssembler groupAssembler;
    private GroupDTOConverter groupConverter;
    
    @Autowired 
    public GroupController(AdministrativeService administrativeService, StudentsService studentsService,
                           GroupModelAssembler groupAssembler, TeachersService teachersService,
                           GroupDTOConverter groupConverter) {
        this.administrativeService = administrativeService;
        this.studentsService = studentsService;
        this.teachersService = teachersService;
        this.groupAssembler = groupAssembler;
        this.groupConverter = groupConverter;
    }
    
    @GetMapping("/restapi/v1/group")
    public CollectionModel<EntityModel<GroupDTO>> getAll() {
        List<EntityModel<GroupDTO>> groups = studentsService.getAllGroups()
                                          .stream()
                                          .map(groupConverter::toDTO)
                                          .map(groupAssembler::toModel)
                                          .collect(Collectors.toList());
        return CollectionModel.of(groups, 
                linkTo(methodOn(GroupController.class).getAll()).withSelfRel());
    }
    
    @GetMapping("/restapi/v1/group/{id}")
    public EntityModel<GroupDTO> getById(@PathVariable Integer id) {
        return groupAssembler.toModel(groupConverter.toDTO(administrativeService.getGroupById(id)));
    }
    
    @PostMapping("/restapi/v1/group")
    public void addGroup(@RequestBody GroupDTO group) {
        administrativeService.createGroup(group.getGroupName(), group.getFaculty().getId());
    }
    
    @PutMapping("/restapi/v1/group")
    public void updateGroup(@RequestBody GroupDTO group) {
        administrativeService.updateGroup(groupConverter.toModel(group));
    }
    
    @DeleteMapping("/restapi/v1/group/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        administrativeService.deleteGroupById(id);
    }
    
    @GetMapping("/restapi/v1/group/{groupId}/assigncourse/{courseId}")
    public EntityModel<GroupDTO> assignGroupsCourse(@PathVariable Integer groupId, @PathVariable Integer courseId) {
        teachersService.assignGroupsCourse(groupId, courseId);
        return getById(groupId);
    }
    
    @GetMapping("/restapi/v1/group/{groupId}/removecourse/{courseId}")
    public EntityModel<GroupDTO> removeGroupsCourse(@PathVariable Integer groupId, @PathVariable Integer courseId) {
        teachersService.deleteGroupsCourse(groupId, courseId);
        return getById(groupId);
    }
}
