package com.foxminded.university.rest.v1.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.university.controller.service.AdministrativeService;
import com.foxminded.university.rest.v1.dto.FacultyDTO;
import com.foxminded.university.rest.v1.dtoconvert.FacultyDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.FacultyModelAssembler;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    AdministrativeService administrativeService;
    @MockBean
    FacultyModelAssembler facultyAssember;
    @MockBean
    FacultyDTOConverter facultyConverter;
    
    @Autowired
    FacultyController facultyController;
    
    @Test
    void getAllShouldCallAdministrativeServiceTest() throws Exception {
        this.mockMvc.perform(get("/restapi/v1/faculty/"))
                    .andExpect(status().isOk());
        
        verify(administrativeService).getAllFaculties();
    }

    @Test
    void getByIdShouldCallAdministrativeServiceTest() throws Exception {
        int facultyId = 10;
        this.mockMvc.perform(get("/restapi/v1/faculty/" + facultyId))
                    .andExpect(status().isOk());
        
        verify(administrativeService).getFacultyById(facultyId);
    }

    @Test
    void addFacultyShouldCallAdministrativeServiceTest() throws Exception {
        String shortName = "short name";
        String fullName = "full name";
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setShortName(shortName);
        facultyDTO.setFullName(fullName);
        
        this.mockMvc.perform(post("/restapi/v1/faculty/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(facultyDTO)))
                    .andExpect(status().isOk());
        
        verify(administrativeService).createFaculty(shortName, fullName);
    }

    @Test
    void updateFacultyShouldCallAdministrativeServiceTest() throws Exception {
        FacultyDTO facultyDTO = new FacultyDTO();
        
        this.mockMvc.perform(put("/restapi/v1/faculty")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(facultyDTO)))
                    .andExpect(status().isOk());
        
        verify(administrativeService).updateFaculty(any());
    }

    @Test
    void deleteFacultyShouldCallAdministrativeServiceTest() throws Exception {
        int facultyId = 55;
        this.mockMvc.perform(delete("/restapi/v1/faculty/" + facultyId))
                    .andExpect(status().isOk());
        
        verify(administrativeService).deleteFaculty(facultyId);
    }

}
