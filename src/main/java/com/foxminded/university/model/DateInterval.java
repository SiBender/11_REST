package com.foxminded.university.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class DateInterval {   
    @NotNull
    private LocalDate startDate;
    
    @NotNull
    private LocalDate endDate;
    
    public DateInterval(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
