package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lesson;
import com.foxminded.university.rest.v1.dto.LessonDTO;

@Component
public class LessonDTOConverter {
    public LessonDTO toDTO(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setDate(lesson.getDate());
        lessonDTO.setClassroom(lesson.getClassroom());
        lessonDTO.setTime(lesson.getTime());
        lessonDTO.setCourse(lesson.getCourse());
        return lessonDTO;
    }
    
    public Lesson toModel(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonDTO.getId());
        lesson.setDate(lessonDTO.getDate());
        lesson.setClassroom(lessonDTO.getClassroom());
        lesson.setTime(lessonDTO.getTime());
        lesson.setCourse(lessonDTO.getCourse());
        return lesson;
    }
}
