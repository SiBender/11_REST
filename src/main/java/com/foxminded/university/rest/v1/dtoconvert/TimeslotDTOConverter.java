package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Timeslot;
import com.foxminded.university.rest.v1.dto.TimeslotDTO;

@Component
public class TimeslotDTOConverter {
    public TimeslotDTO toDTO(Timeslot timeslot) {
        TimeslotDTO timeslotDTO = new TimeslotDTO();
        timeslotDTO.setId(timeslot.getId());
        timeslotDTO.setDescription(timeslot.getDescription());
        return timeslotDTO;
    }
    
    public Timeslot toModel(TimeslotDTO timeslotDTO) {
        Timeslot timeslot = new Timeslot();
        timeslot.setId(timeslotDTO.getId());
        timeslot.setDescription(timeslotDTO.getDescription());
        return timeslot;
    }
}
