package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Classroom;
import com.foxminded.university.rest.v1.dto.ClassroomDTO;

class ClassroomDTOConverterTest {
    ClassroomDTOConverter classroomConverter = new ClassroomDTOConverter();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int capacity = 10;
        int id = 1;
        String number = "number";
        Classroom classroom = new Classroom();
        classroom.setCapacity(capacity);
        classroom.setId(id);
        classroom.setNumber(number);
        
        ClassroomDTO classroomDTO = classroomConverter.toDTO(classroom);
        assertEquals(capacity, classroomDTO.getCapacity());
        assertEquals(id, classroomDTO.getId());
        assertEquals(number, classroomDTO.getNumber());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int capacity = 10;
        int id = 1;
        String number = "number";
        
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setCapacity(capacity);
        classroomDTO.setId(id);
        classroomDTO.setNumber(number);
    
        Classroom classroom = classroomConverter.toModel(classroomDTO);
        assertEquals(capacity, classroom.getCapacity());
        assertEquals(id, classroom.getId());
        assertEquals(number, classroom.getNumber());
    }

}
