package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.foxminded.university.controller.util.DateIntervalGenerator;
import com.foxminded.university.model.DateInterval;
import com.foxminded.university.model.Lesson;
import com.foxminded.university.model.Timetable;
import com.foxminded.university.rest.v1.dto.LessonDTO;
import com.foxminded.university.rest.v1.dto.TimetableDTO;

class TimetableDTOConverterTest {
    TimetableDTOConverter timetableDTOConverter = new TimetableDTOConverter(new LessonDTOConverter());
    DateIntervalGenerator dateIntervalGenerator = new DateIntervalGenerator();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        DateInterval dateInterval = dateIntervalGenerator.getCurrentWeek();
        Lesson lesson = new Lesson();
        int lessonId = 100;
        lesson.setId(lessonId);
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        
        Timetable timetable = new Timetable();
        timetable.setDateInterval(dateInterval);
        timetable.setLessons(lessons);
        
        TimetableDTO timetableDTO = timetableDTOConverter.toDTO(timetable);
        
        assertSame(dateInterval, timetableDTO.getDateInterval());
        assertEquals(lessonId, timetableDTO.getLessons().get(0).getId());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        DateInterval dateInterval = dateIntervalGenerator.getCurrentWeek();
        LessonDTO lessonDTO = new LessonDTO();
        int lessonId = 100;
        lessonDTO.setId(lessonId);
        ArrayList<LessonDTO> lessons = new ArrayList<>();
        lessons.add(lessonDTO);
        
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setDateInterval(dateInterval);
        timetableDTO.setLessons(lessons);
        
        Timetable timetable = timetableDTOConverter.toModel(timetableDTO);
        
        assertSame(dateInterval, timetable.getDateInterval());
        assertEquals(lessonId, timetable.getLessons().get(0).getId());
    }

}
