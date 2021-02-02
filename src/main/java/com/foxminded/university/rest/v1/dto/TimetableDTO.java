package com.foxminded.university.rest.v1.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.foxminded.university.model.DateInterval;

public class TimetableDTO extends RepresentationModel<TimetableDTO> {
    private static final String DATE_PATTERN = "YYYY-MM-DD";
    private List<LessonDTO> lessons;
    private DateInterval dateInterval;
    
    public List<LessonDTO> getLessons() {
        return lessons;
    }
    public void setLessons(List<LessonDTO> lessons) {
        this.lessons = lessons;
    }
    public DateInterval getDateInterval() {
        return dateInterval;
    }
    public void setDateInterval(DateInterval dateInterval) {
        this.dateInterval = dateInterval;
    }
    
    public String getDatePattern() {
        return DATE_PATTERN;
    }
}
