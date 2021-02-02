package com.foxminded.university.rest.v1.controller;

import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.university.controller.service.LessonService;
import com.foxminded.university.rest.v1.dto.ClassroomDTO;
import com.foxminded.university.rest.v1.dtoconvert.ClassroomDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.ClassroomModelAssembler;

@WebMvcTest(controllers = ClassroomController.class)
class ClassroomControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    LessonService lessonService;
    @MockBean
    ClassroomDTOConverter converter;
    @MockBean
    ClassroomModelAssembler classroomAssembler;
    
    @Autowired
    ClassroomController classroomController;
    
    @Test
    void getAllShouldCallLessonServiceTest() throws Exception {
        this.mockMvc.perform(get("/restapi/v1/classroom"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("classroom")));
        
        verify(lessonService).getAllClassrooms();
    }

    @Test
    void getByIdShouldCallLessonServiceTest() throws Exception {
        int classroomId = 1;
        this.mockMvc.perform(get("/restapi/v1/classroom/" + classroomId))
                    .andExpect(status().isOk());

        verify(lessonService).getClassroomById(classroomId);
    }

    @Test
    void addClassroomShouldCallLessonServiceTest() throws Exception {
        ClassroomDTO classroom = new ClassroomDTO();
        classroom.setCapacity(10);
        classroom.setNumber("num");
        this.mockMvc.perform(post("/restapi/v1/classroom")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(classroom)));
                
        verify(lessonService).addClassroom(any());
    }

    @Test
    void updateClassroomShouldCallLessonServiceTest() throws Exception {
        ClassroomDTO classroom = new ClassroomDTO();
        classroom.setId(100);
        classroom.setCapacity(10);
        classroom.setNumber("num");
        this.mockMvc.perform(put("/restapi/v1/classroom")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(classroom)));
                
        verify(lessonService).updateClassroom(any());
    }

    @Test
    void deleteClassroomShouldCallLessonServiceTest() throws Exception {
        ClassroomDTO classroom = new ClassroomDTO();
        int classroomId = 100;
        classroom.setId(classroomId);
        classroom.setCapacity(10);
        classroom.setNumber("num");
        this.mockMvc.perform(delete("/restapi/v1/classroom")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(classroom)));
                
        verify(lessonService).deleteClassroom(classroomId);
    }

    @Test
    void deleteClassroomByIdShouldCallLessonServiceTest() throws Exception {
        int classroomId = 200;
        this.mockMvc.perform(delete("/restapi/v1/classroom/" + classroomId));
                
        verify(lessonService).deleteClassroom(classroomId);
    }
}
