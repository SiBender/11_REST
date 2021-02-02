package com.foxminded.university.rest.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.controller.service.LessonService;
import com.foxminded.university.rest.v1.dto.LessonDTO;
import com.foxminded.university.rest.v1.dtoconvert.LessonDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.LessonModelAssembler;

@RestController
public class LessonController {
    private LessonService lessonService;
    private LessonModelAssembler lessonAssembler;
    private LessonDTOConverter lessonConverter;
    
    @Autowired
    public LessonController(LessonService lessonService, LessonModelAssembler lessonAssembler,
                            LessonDTOConverter lessonConverter) {
        this.lessonService = lessonService;
        this.lessonAssembler = lessonAssembler;
        this.lessonConverter = lessonConverter;
    }
    
    @GetMapping("/restapi/v1/lesson/{id}")
    public EntityModel<LessonDTO> getById(@PathVariable Integer id) {
        return lessonAssembler.toModel(lessonConverter.toDTO(lessonService.getLessonById(id)));
    }
    
    @PostMapping("/restapi/v1/lesson")
    public void addLesson(@RequestBody LessonDTO lesson) {
        lessonService.createLesson(lesson.getDate().toString(), lesson.getTime().getId(), 
                                   lesson.getClassroom().getId(), lesson.getCourse().getId());
    }
    
    @PutMapping("/restapi/v1/lesson")
    public void updateLesson(@RequestBody LessonDTO lesson) {
        lessonService.updateLesson(lessonConverter.toModel(lesson));
    }
    
    @DeleteMapping("/restapi/v1/lesson/{id}")
    public void deleteLessonById(@PathVariable Integer id) {
        lessonService.deleteLesson(id);
    }
}
