package com.foxminded.university.controller.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.controller.service.StudentsService;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Student;

@Controller
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    
    @GetMapping("/student")
    public String studentsInfo(Model model) {        
        List<Group> groups = studentsService.getAllGroups();
        model.addAttribute("groups", groups);
        model.addAttribute("groupsCount", groups.size());
        model.addAttribute("student", new Student());
        
        int[] studentsCount = {0};
        groups.forEach(group -> studentsCount[0] += group.getStudents().size());
        model.addAttribute("studentsCount", studentsCount[0]);
        
        return "student";
    }
    
    @PostMapping("/student")
    public String addStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        if(student.getGroup() == null || student.getGroup().getId() < 1) {
            bindingResult.rejectValue("group.id", "error.student", "Choose a group"); 
        }
        
        if(bindingResult.hasErrors()) {
            List<Group> groups = studentsService.getAllGroups();
            model.addAttribute("groups", groups);
            
            int[] studentsCount = {0};
            groups.forEach(group -> studentsCount[0] += group.getStudents().size());
            model.addAttribute("studentsCount", studentsCount[0]);
            return "student";
        }
        
        studentsService.addStudent(student.getFirstName(), student.getLastName(), student.getGroup().getId());
        return "redirect:/student";
    }
    
    @GetMapping("/deletestudent")
    public String deleteStudent(@RequestParam String id, Model model) {
        try {
            int studentId = Integer.parseInt(id);
            studentsService.deleteStudent(studentId);
        } catch (NumberFormatException ex) {
            //log error
        }
        return "redirect:/student";
    }
    
    @GetMapping("/student/editstudent")
    public String getStudent(@RequestParam String id, Model model)  {
        try {
            int studentId = Integer.parseInt(id);
            model.addAttribute("student", studentsService.getStudent(studentId));
            model.addAttribute("groups", studentsService.getAllGroups());
        } catch (NumberFormatException ex) {
            //log error
        }
        return "student/editstudent";
    }
    
    @PostMapping("/student/editstudent")
    public String updateStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        if(student.getGroup() == null || student.getGroup().getId() < 1) {
            bindingResult.rejectValue("group.id", "error.student", "Choose a group"); 
        }
        
        if(bindingResult.hasErrors()) {
            model.addAttribute("groups", studentsService.getAllGroups());
            return "student/editstudent";
        }
        
        studentsService.update(student);
        return "redirect:/student";
    }
}
