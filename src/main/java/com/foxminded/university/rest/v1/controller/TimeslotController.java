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

import com.foxminded.university.controller.service.LessonService;
import com.foxminded.university.rest.v1.dto.TimeslotDTO;
import com.foxminded.university.rest.v1.dtoconvert.TimeslotDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.TimeslotModelAssembler;

@RestController
public class TimeslotController {
    private LessonService lessonService;
    private TimeslotModelAssembler timeslotAssembler;
    private TimeslotDTOConverter timeslotConverter;
    
    @Autowired
    public TimeslotController(LessonService lessonService, TimeslotModelAssembler timeslotAssembler, 
                              TimeslotDTOConverter timeslotConverter) {
        this.lessonService = lessonService;
        this.timeslotAssembler = timeslotAssembler;
        this.timeslotConverter = timeslotConverter;
    }
    
    @GetMapping("/restapi/v1/timeslot")
    public CollectionModel<EntityModel<TimeslotDTO>> getAll() { 
        List<EntityModel<TimeslotDTO>> timeslots = lessonService.getAllTimeslots()
                                                                .stream()
                                                                .map(timeslotConverter::toDTO)
                                                                .map(timeslotAssembler::toModel)
                                                                .collect(Collectors.toList());
        return CollectionModel.of(timeslots, 
                linkTo(methodOn(TimeslotController.class).getAll()).withSelfRel());
    }
    
    @GetMapping("/restapi/v1/timeslot/{id}")
    public EntityModel<TimeslotDTO> getById(@PathVariable Integer id) {
        return timeslotAssembler.toModel(timeslotConverter.toDTO(lessonService.getTimeslotById(id)));
    }
    
    @PostMapping("/restapi/v1/timeslot")
    public void addTimeslot(@RequestBody TimeslotDTO timeslot) {
        lessonService.addTimeslot(timeslotConverter.toModel(timeslot));
    }
    
    @PutMapping("/restapi/v1/timeslot")
    public void updateTimeslot(@RequestBody TimeslotDTO timeslot) {
        lessonService.updateTimeslot(timeslotConverter.toModel(timeslot));
    }
    
    @DeleteMapping("/restapi/v1/timeslot/{id}")
    public void deleteTimeslotById(@PathVariable Integer id) {
        lessonService.deleteClassroom(id);
    }
}
