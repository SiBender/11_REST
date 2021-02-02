package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.rest.v1.dto.GroupDTO;

@Component
public class GroupDTOConverter {
    public GroupDTO toDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setGroupName(group.getGroupName());
        groupDTO.setFaculty(group.getFaculty());
        groupDTO.setStudents(group.getStudents());
        groupDTO.setCourses(group.getCourses());
        return groupDTO;
    }
    
    public Group toModel(GroupDTO groupDTO) {
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setGroupName(groupDTO.getGroupName());
        group.setFaculty(groupDTO.getFaculty());
        group.setStudents(groupDTO.getStudents());
        group.setCourses(groupDTO.getCourses());
        return group;
    }
}
