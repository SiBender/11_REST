package com.foxminded.university.rest.v1.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.university.controller.service.AdministrativeService;
import com.foxminded.university.controller.service.StudentsService;
import com.foxminded.university.controller.service.TeachersService;
import com.foxminded.university.model.Faculty;
import com.foxminded.university.rest.v1.dto.GroupDTO;
import com.foxminded.university.rest.v1.dtoconvert.GroupDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.GroupModelAssembler;

@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AdministrativeService administrativeService;
    @MockBean
    private StudentsService studentsService;
    @MockBean
    private TeachersService teachersService;
    @MockBean
    private GroupModelAssembler groupAssembler;
    @MockBean
    private GroupDTOConverter groupConverter;
    
    @Autowired
    GroupController groupController;
    
    @Test
    void getAllShouldCallStudentsServiceTest() throws Exception {
        this.mockMvc.perform(get("/restapi/v1/group"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("group")));

        verify(studentsService).getAllGroups();
    }

    @Test
    void getByIdShouldCallAdministrativeServiceTest() throws Exception {
        int groupId = 20;
        this.mockMvc.perform(get("/restapi/v1/group/" + groupId))
                    .andExpect(status().isOk());

        verify(administrativeService).getGroupById(groupId);
    }

    @Test
    void addGroupShouldCallAdministrativeServiceTest() throws Exception {
        String groupName = "test group";
        int facultyId = 30;
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName(groupName);
        groupDTO.setFaculty(faculty);
        
        this.mockMvc.perform(post("/restapi/v1/group")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(groupDTO)))
                    .andExpect(status().isOk());
        
        verify(administrativeService).createGroup(groupName, facultyId);
    }

    @Test
    void updateGroupShouldCallAdministrativeServiceTest() throws Exception {
        int groupId = 11;
        String groupName = "up test group";
        int facultyId = 31;
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(groupId);
        groupDTO.setGroupName(groupName);
        groupDTO.setFaculty(faculty);
        
        this.mockMvc.perform(put("/restapi/v1/group")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(groupDTO)))
                    .andExpect(status().isOk());
        
        verify(administrativeService).updateGroup(any());
    }

    @Test
    void deleteGroupShouldCallAdministrativeServiceTest() throws Exception {
        int groupId = 40;
        
        this.mockMvc.perform(delete("/restapi/v1/group/" + groupId))
                    .andExpect(status().isOk());
        
        verify(administrativeService).deleteGroupById(groupId);
    }

    @Test
    void assignGroupsCourseShouldCallTeachersServiceServiceTest() throws Exception {
        int groupId = 50;
        int courseId = 60;
        
        this.mockMvc.perform(get("/restapi/v1/group/"+ groupId + "/assigncourse/" + courseId))
                    .andExpect(status().isOk());
        
        verify(teachersService).assignGroupsCourse(groupId, courseId);
    }

    @Test
    void removeGroupsCourseShouldCallTeachersServiceServiceTest() throws Exception {
        int groupId = 51;
        int courseId = 61;
        
        this.mockMvc.perform(get("/restapi/v1/group/"+ groupId + "/removecourse/" + courseId))
                    .andExpect(status().isOk());
        
        verify(teachersService).deleteGroupsCourse(groupId, courseId);
    }

}
