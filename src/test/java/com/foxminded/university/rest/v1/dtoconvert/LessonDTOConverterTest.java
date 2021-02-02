package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Classroom;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Lesson;
import com.foxminded.university.model.Timeslot;
import com.foxminded.university.rest.v1.dto.LessonDTO;

class LessonDTOConverterTest {
    LessonDTOConverter lessonConverter = new LessonDTOConverter();
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        LocalDate date = LocalDate.now();
        Classroom classroom = new Classroom();
        Timeslot time = new Timeslot();       
        Course course = new Course();
        
        Lesson lesson = new Lesson();
        lesson.setId(id);
        lesson.setDate(date);
        lesson.setClassroom(classroom);
        lesson.setTime(time);
        lesson.setCourse(course);
        
        LessonDTO lessonDTO = lessonConverter.toDTO(lesson);
        
        assertEquals(id, lessonDTO.getId());
        assertSame(date, lessonDTO.getDate());
        assertSame(classroom, lessonDTO.getClassroom());
        assertSame(time, lessonDTO.getTime());
        assertSame(course, lessonDTO.getCourse());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        LocalDate date = LocalDate.now();
        Classroom classroom = new Classroom();
        Timeslot time = new Timeslot();       
        Course course = new Course();
        
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(id);
        lessonDTO.setDate(date);
        lessonDTO.setClassroom(classroom);
        lessonDTO.setTime(time);
        lessonDTO.setCourse(course);
        
        Lesson lesson = lessonConverter.toModel(lessonDTO);
        
        assertEquals(id, lesson.getId());
        assertSame(date, lesson.getDate());
        assertSame(classroom, lesson.getClassroom());
        assertSame(time, lesson.getTime());
        assertSame(course, lesson.getCourse());
    }

}
