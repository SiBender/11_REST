package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Faculty;
import com.foxminded.university.rest.v1.dto.FacultyDTO;

@Component
public class FacultyDTOConverter {
    public FacultyDTO toDTO(Faculty faculty) {
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setShortName(faculty.getShortName());
        facultyDTO.setFullName(faculty.getFullName());
        return facultyDTO;
    }
    
    public Faculty toModel(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyDTO.getId());
        faculty.setShortName(facultyDTO.getShortName());
        faculty.setFullName(facultyDTO.getFullName());
        return faculty;
    }
}
