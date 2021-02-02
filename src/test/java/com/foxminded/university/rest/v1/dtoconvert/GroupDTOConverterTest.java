package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Faculty;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Student;
import com.foxminded.university.rest.v1.dto.GroupDTO;

class GroupDTOConverterTest {
    GroupDTOConverter groupConverter = new GroupDTOConverter();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String groupName = "droup name";
        Faculty faculty = new Faculty();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        Group group = new Group();
        group.setId(id);
        group.setGroupName(groupName);
        group.setFaculty(faculty);
        group.setStudents(students);
        group.setCourses(courses);
        
        GroupDTO groupDTO = groupConverter.toDTO(group);
        assertEquals(id, groupDTO.getId());
        assertEquals(groupName, groupDTO.getGroupName());
        assertSame(faculty, groupDTO.getFaculty());
        assertSame(students, groupDTO.getStudents());
        assertSame(courses, groupDTO.getCourses());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String groupName = "droup name";
        Faculty faculty = new Faculty();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(id);
        groupDTO.setGroupName(groupName);
        groupDTO.setFaculty(faculty);
        groupDTO.setStudents(students);
        groupDTO.setCourses(courses);
        
        Group group = groupConverter.toModel(groupDTO);
        assertEquals(id, group.getId());
        assertEquals(groupName, group.getGroupName());
        assertSame(faculty, group.getFaculty());
        assertSame(students, group.getStudents());
        assertSame(courses, group.getCourses());
    }

}
