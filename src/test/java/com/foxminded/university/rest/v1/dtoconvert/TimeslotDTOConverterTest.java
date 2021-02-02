package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Timeslot;
import com.foxminded.university.rest.v1.dto.TimeslotDTO;

class TimeslotDTOConverterTest {
    TimeslotDTOConverter timeslotDTOConverter = new TimeslotDTOConverter();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String description = "description";
        
        Timeslot timeslot = new Timeslot();
        timeslot.setId(id);
        timeslot.setDescription(description);
        
        TimeslotDTO timeslotDTO = timeslotDTOConverter.toDTO(timeslot);
        
        assertEquals(id, timeslotDTO.getId());
        assertEquals(description, timeslotDTO.getDescription());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String description = "description";
        
        TimeslotDTO timeslotDTO = new TimeslotDTO();
        timeslotDTO.setId(id);
        timeslotDTO.setDescription(description);
        
        Timeslot timeslot = timeslotDTOConverter.toModel(timeslotDTO);
        
        assertEquals(id, timeslot.getId());
        assertEquals(description, timeslot.getDescription());
    }

}
