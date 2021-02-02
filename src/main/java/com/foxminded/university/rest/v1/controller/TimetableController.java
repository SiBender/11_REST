package com.foxminded.university.rest.v1.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.controller.service.TimetableService;
import com.foxminded.university.rest.v1.dto.TimetableDTO;
import com.foxminded.university.rest.v1.dtoconvert.TimetableDTOConverter;

@RestController
public class TimetableController {
    private TimetableService timetableService;
    private TimetableDTOConverter timetableConverter;
    
    @Autowired
    public TimetableController(TimetableService timetableService, TimetableDTOConverter timetableConverter) {
        this.timetableService = timetableService;
        this.timetableConverter = timetableConverter;
    }
    
    @GetMapping("/restapi/v1/timetable/student")
    public EntityModel<TimetableDTO> getStudentTimetable(@RequestParam Integer studentId, 
                                                      @RequestParam String stDate, 
                                                      @RequestParam String endDate) {
        TimetableDTO timetable = timetableConverter.toDTO(
                                    timetableService.getStudentTimetable(stDate, endDate, studentId));
        timetable.add(linkTo(
                        methodOn(TimetableController.class).getStudentTimetable(studentId, stDate, stDate))
                            .withSelfRel());
        timetable.getLessons()
                 .forEach(l -> l.add(linkTo(methodOn(LessonController.class).getById(l.getId())).withSelfRel()));
        return EntityModel.of(timetable);
    }
    
    @GetMapping("/restapi/v1/timetable/teacher")
    public EntityModel<TimetableDTO> getTeacherTimetable(@RequestParam Integer teacherId, 
                                                      @RequestParam String stDate, 
                                                      @RequestParam String endDate) {
        TimetableDTO timetable = timetableConverter.toDTO(
                                    timetableService.getTeacherTimetable(stDate, endDate, teacherId));
        timetable.add(linkTo(
                        methodOn(TimetableController.class).getTeacherTimetable(teacherId, stDate, endDate))
                            .withSelfRel());
        timetable.getLessons()
                 .forEach(l -> l.add(linkTo(
                                     methodOn(LessonController.class).getById(l.getId())).withSelfRel()));
        return EntityModel.of(timetable);
    }
}
