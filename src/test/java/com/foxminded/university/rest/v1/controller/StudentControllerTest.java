package com.foxminded.university.rest.v1.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.university.controller.service.StudentsService;
import com.foxminded.university.model.Group;
import com.foxminded.university.rest.v1.dto.StudentDTO;
import com.foxminded.university.rest.v1.dtoconvert.StudentDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.StudentModelAssembler;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    StudentsService studentsService;
    @MockBean
    StudentModelAssembler studentAssembler;
    @MockBean
    StudentDTOConverter studentConverter;
    
    @Autowired
    StudentController studentController;
    
    @Test
    void getByIdShouldCallStudentsServiceTest() throws Exception {
        int studentId = 2;
        
        this.mockMvc.perform(get("/restapi/v1/student/" + studentId))
                    .andExpect(status().isOk());

        verify(studentsService).getStudent(studentId);
    }

    @Test
    void addStudentShouldCallStudentsServiceTest() throws Exception {
        int groupId = 40;
        String firstName = "first name";
        String lastName = "last name";
        Group group = new Group();
        group.setId(groupId);
        
        StudentDTO student = new StudentDTO();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroup(group);
        
        this.mockMvc.perform(post("/restapi/v1/student")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(student)))
                    .andExpect(status().isOk());
   
        verify(studentsService).addStudent(firstName, lastName, groupId);
    }

    @Test
    void updateStudentShouldCallStudentsServiceTest() throws Exception {
        StudentDTO student = new StudentDTO();
        
        this.mockMvc.perform(put("/restapi/v1/student")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(student)))
                    .andExpect(status().isOk());
   
        verify(studentsService).update(any());
    }

    @Test
    void deleteStudentByIdShouldCallStudentsServiceTest() throws Exception {
        int studentId = 200;
        this.mockMvc.perform(delete("/restapi/v1/student/" + studentId));
                
        verify(studentsService).deleteStudent(studentId);
    }

}
