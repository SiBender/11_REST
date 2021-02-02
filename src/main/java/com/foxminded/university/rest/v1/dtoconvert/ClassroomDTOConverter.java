package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Classroom;
import com.foxminded.university.rest.v1.dto.ClassroomDTO;

@Component
public class ClassroomDTOConverter {
    
    public ClassroomDTO toDTO(Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setId(classroom.getId());
        classroomDTO.setNumber(classroom.getNumber());
        classroomDTO.setCapacity(classroom.getCapacity());
        return classroomDTO;
    }

    public Classroom toModel(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setId(classroomDTO.getId());
        classroom.setNumber(classroomDTO.getNumber());
        classroom.setCapacity(classroomDTO.getCapacity());
        return classroom;
    }
}
