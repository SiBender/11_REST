package com.foxminded.university.rest.v1.dtoconvert;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Timetable;
import com.foxminded.university.rest.v1.dto.TimetableDTO;

@Component
public class TimetableDTOConverter {    
    private LessonDTOConverter lessonConverter;
    
    @Autowired
    public TimetableDTOConverter(LessonDTOConverter lessonConverter) {
        this.lessonConverter = lessonConverter;
    }

    public TimetableDTO toDTO(Timetable timetable) {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setDateInterval(timetable.getDateInterval());
        timetableDTO.setLessons(timetable.getLessons()
                                         .stream()
                                         .map(lessonConverter::toDTO)
                                         .collect(Collectors.toList()));
        return timetableDTO;
    }
    
    public Timetable toModel(TimetableDTO timetableDTO) {
        Timetable timetable = new Timetable();
        timetable.setDateInterval(timetableDTO.getDateInterval());
        timetable.setLessons(timetableDTO.getLessons()
                                         .stream()
                                         .map(lessonConverter::toModel)
                                         .collect(Collectors.toList()));
        return timetable;
    }
}
