package com.foxminded.university.rest.v1.dto;

import org.springframework.hateoas.RepresentationModel;

public class ClassroomDTO extends RepresentationModel<ClassroomDTO> {
    private int id;
    private String number;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
