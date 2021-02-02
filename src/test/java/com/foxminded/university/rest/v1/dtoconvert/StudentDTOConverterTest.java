package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Student;
import com.foxminded.university.rest.v1.dto.StudentDTO;

class StudentDTOConverterTest {
    StudentDTOConverter studentConverter = new StudentDTOConverter();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String firstName = "firstName";
        String lastName = "lastName";
        Group group = new Group();
        
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroup(group);
        
        StudentDTO studentDTO = studentConverter.toDTO(student);
        
        assertEquals(id, studentDTO.getId());
        assertEquals(firstName, studentDTO.getFirstName());
        assertEquals(lastName, studentDTO.getLastName());
        assertSame(group, studentDTO.getGroup());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String firstName = "firstName";
        String lastName = "lastName";
        Group group = new Group();
        
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(id);
        studentDTO.setFirstName(firstName);
        studentDTO.setLastName(lastName);
        studentDTO.setGroup(group);
        
        Student student = studentConverter.toModel(studentDTO);
        
        assertEquals(id, student.getId());
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertSame(group, student.getGroup());
    }

}
