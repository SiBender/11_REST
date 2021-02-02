package com.foxminded.university.rest.v1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.university.controller.service.TeachersService;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.rest.v1.dto.CourseDTO;
import com.foxminded.university.rest.v1.dtoconvert.CourseDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.CourseModelAssembler;

@WebMvcTest(controllers = CourseController.class)
class CourseControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    TeachersService teachersService;
    @MockBean
    CourseModelAssembler courseAssembler;
    @MockBean
    CourseDTOConverter courseConverter;
    @Autowired
    CourseController courseController;
    
    @Test
    void getByIdShouldCallTeachersServiceTest() throws Exception {
        int courseId = 100;
        
        this.mockMvc.perform(get("/restapi/v1/course/" + courseId))
                    .andExpect(status().isOk());
        
        verify(teachersService).getCourse(courseId);
    }

    @Test
    void addCourseShouldCallTeachersServiceTest() throws Exception {
        String name = "course name";
        String description = "descr";
        Teacher teacher = new Teacher();
        int teacherId = 200;
        teacher.setId(teacherId);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(name);
        courseDTO.setDescription(description);
        courseDTO.setTeacher(teacher);
        
        this.mockMvc.perform(post("/restapi/v1/course/")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(courseDTO)))
                    .andExpect(status().isOk());
        
        verify(teachersService).createCourse(name, description, teacherId);
    }

    @Test
    void updateCourseShouldCallTeachersServiceTest() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        
        this.mockMvc.perform(put("/restapi/v1/course/")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(courseDTO)))
                    .andExpect(status().isOk());
        
        verify(teachersService).updateCourse(any());
    }
}
