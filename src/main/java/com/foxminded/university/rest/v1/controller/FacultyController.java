package com.foxminded.university.rest.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.controller.service.AdministrativeService;
import com.foxminded.university.rest.v1.dto.FacultyDTO;
import com.foxminded.university.rest.v1.dtoconvert.FacultyDTOConverter;
import com.foxminded.university.rest.v1.modelassembler.FacultyModelAssembler;

@RestController
public class FacultyController {
    private AdministrativeService administrativeService;
    private FacultyModelAssembler facultyAssember;
    private FacultyDTOConverter facultyConverter;
    
    @Autowired 
    public FacultyController(AdministrativeService administrativeService, 
                             FacultyModelAssembler facultyAssember, FacultyDTOConverter facultyConverter) {
        this.administrativeService = administrativeService;
        this.facultyAssember = facultyAssember;
        this.facultyConverter = facultyConverter;
    }
    
    @GetMapping("/restapi/v1/faculty")
    public CollectionModel<EntityModel<FacultyDTO>> getAll() {
        List<EntityModel<FacultyDTO>> faculties = administrativeService.getAllFaculties().stream()
                                               .map(facultyConverter::toDTO)
                                               .map(facultyAssember::toModel)
                                               .collect(Collectors.toList());
        return CollectionModel.of(faculties, 
                linkTo(methodOn(FacultyController.class).getAll()).withSelfRel());
    }
    
    @GetMapping("/restapi/v1/faculty/{id}")
    public EntityModel<FacultyDTO> getById(@PathVariable Integer id) {
        return facultyAssember.toModel(facultyConverter.toDTO(administrativeService.getFacultyById(id)));
    }
    
    @PostMapping("/restapi/v1/faculty")
    public void addFaculty(@RequestBody FacultyDTO faculty) {
        administrativeService.createFaculty(faculty.getShortName(), faculty.getFullName());
    }
    
    @PutMapping("/restapi/v1/faculty")
    public void updateFaculty(@RequestBody FacultyDTO faculty) {
        administrativeService.updateFaculty(facultyConverter.toModel(faculty));
    }
    
    @DeleteMapping("/restapi/v1/faculty/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        administrativeService.deleteFaculty(id);
    }
}
