package com.foxminded.university.rest.v1.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.foxminded.university.controller.service.LessonService;
import com.foxminded.university.model.Classroom;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Timeslot;
import com.foxminded.university.rest.v1.dto.LessonDTO;
import com.foxminded.university.rest.v1.dtoconvert.LessonDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.LessonModelAssembler;

@WebMvcTest(controllers = LessonController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LessonControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    LessonService lessonService;
    @MockBean
    LessonModelAssembler lessonAssembler;
    @MockBean
    LessonDTOConverter lessonConverter;
    
    @Autowired
    LessonController lessonController;
    
    @BeforeAll
    void init() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    
    
    @Test
    void getByIdShouldCallLessonServiceTest() throws Exception {
        int lessonId = 20;
        
        this.mockMvc.perform(get("/restapi/v1/lesson/" + lessonId))
                    .andExpect(status().isOk());

        verify(lessonService).getLessonById(lessonId);
    }

    @Test
    void addLessonShouldCallLessonServiceTest() throws Exception {
        LocalDate lessonDate = LocalDate.now();
        String lessonDateString = lessonDate.toString();
        int timeslotId = 10;
        int classroomId = 20;
        int courseId = 30;
        
        Timeslot timeslot = new Timeslot();
        timeslot.setId(timeslotId);
        Classroom classroom = new Classroom();
        classroom.setId(classroomId);
        Course course = new Course();
        course.setId(courseId);
        
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setDate(lessonDate);
        lessonDTO.setTime(timeslot);
        lessonDTO.setClassroom(classroom);
        lessonDTO.setCourse(course);
        
        this.mockMvc.perform(post("/restapi/v1/lesson")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsBytes(lessonDTO)))
                    .andExpect(status().isOk());
        

        verify(lessonService).createLesson(lessonDateString, timeslotId, classroomId, courseId);
    }

    @Test
    void updateLessonShouldCallLessonServiceTest() throws Exception {
        LocalDate lessonDate = LocalDate.now();
        int lessonId = 1;
        int timeslotId = 10;
        int classroomId = 20;
        int courseId = 30;
        
        Timeslot timeslot = new Timeslot();
        timeslot.setId(timeslotId);
        Classroom classroom = new Classroom();
        classroom.setId(classroomId);
        Course course = new Course();
        course.setId(courseId);
        
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lessonId);
        lessonDTO.setDate(lessonDate);
        lessonDTO.setTime(timeslot);
        lessonDTO.setClassroom(classroom);
        lessonDTO.setCourse(course);
        
        this.mockMvc.perform(put("/restapi/v1/lesson")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(lessonDTO)))
                    .andExpect(status().isOk());
        
        verify(lessonService).updateLesson(any());
    }

    @Test
    void deleteLessonByIdShouldCallLessonServiceTest() throws Exception {
        int lessonId = 100;
        
        this.mockMvc.perform(delete("/restapi/v1/lesson/" + lessonId))
                    .andExpect(status().isOk());

        verify(lessonService).deleteLesson(lessonId);
    }
}
