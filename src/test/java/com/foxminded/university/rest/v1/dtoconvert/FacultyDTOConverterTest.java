package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Faculty;
import com.foxminded.university.rest.v1.dto.FacultyDTO;

class FacultyDTOConverterTest {
    FacultyDTOConverter facultyConverter = new FacultyDTOConverter();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String shortName = "short name";
        String fullName = "full name";
        
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setShortName(shortName);
        faculty.setFullName(fullName);
        
        FacultyDTO facultyDTO = facultyConverter.toDTO(faculty);
        
        assertEquals(id, facultyDTO.getId());
        assertEquals(shortName, facultyDTO.getShortName());
        assertEquals(fullName, facultyDTO.getFullName());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String shortName = "short name";
        String fullName = "full name";
        
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(id);
        facultyDTO.setShortName(shortName);
        facultyDTO.setFullName(fullName);
        
        Faculty faculty = facultyConverter.toModel(facultyDTO);
        
        assertEquals(id, faculty.getId());
        assertEquals(shortName, faculty.getShortName());
        assertEquals(fullName, faculty.getFullName());
    }
}
