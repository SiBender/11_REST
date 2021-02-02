package com.foxminded.university.controller.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.controller.service.TeachersService;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Teacher;

@Controller
public class TeachersController {
    private TeachersService teachersService;
    
    @Autowired
    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }
    
    @RequestMapping("/teacher")
    public String teachersInfo(Model model) {
        List<Teacher> teachers = teachersService.getAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("course", new Course());
        
        List<Group> groups = teachersService.getAllGroups();
        model.addAttribute("groups", groups);
        return "teacher";
    }
    
    @GetMapping("teacher/addcourse")
    public String showCourseForm(Model model) {
        List<Teacher> teachers = teachersService.getAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("course", new Course());
        return "teacher/addcourse";
    }
    
    @PostMapping("teacher/addcourse")
    public String addCourse(@Valid Course course, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teachersService.getAll());
            return "teacher/addcourse";
        }
        teachersService.createCourse(course.getName(), course.getDescription(), course.getTeacher().getId());
        return "redirect:/teacher";
    }
    
    @GetMapping("/deletecourse")
    public String deleteCourse(@RequestParam String id, Model model) {
        try {
            int courseId = Integer.parseInt(id);
            teachersService.deleteCourse(courseId);
        } catch (NumberFormatException ex) {
            //log error
            return "redirect:/teacher";
        }
        return "redirect:/teacher";
    }
    
    @GetMapping("/teacher/editcourse")
    public String getCourse(@RequestParam String id, Model model) {
        try {
            int courseId = Integer.parseInt(id);
            model.addAttribute("teachers", teachersService.getAll());
            model.addAttribute("course", teachersService.getCourse(courseId));
        } catch (NumberFormatException ex) {
            //log error
            return "teacher";
        }
        return "teacher/editcourse";
    }
    
    @PostMapping("/teacher/editcourse")
    public String updateCourse(@Valid Course course, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teachersService.getAll());
            return "teacher/editcourse";
        }
        teachersService.updateCourse(course);
        return "redirect:/teacher";
    }
    
    @GetMapping("/deletegroupscourse")
    public String deleteGroupsCourse(@RequestParam int cid, @RequestParam int gid,  Model model) {
        teachersService.deleteGroupsCourse(gid, cid);
        return "redirect:/teacher";
    }
    
    @GetMapping("/teacher/assigncourse")
    public String getGroupsCoursesInfo(@RequestParam int gid,  Model model) {
        Group group = teachersService.getGroupById(gid);
        model.addAttribute("group", group);
        model.addAttribute("courses", teachersService.getFreeCourses(group));
        return "teacher/assigncourse";
    }
    
    @PostMapping("/teacher/assigncourse")
    public String assignGroupsCourse(@ModelAttribute Group group, Model model) {
        teachersService.assignGroupsCourse(group.getId(), group.getCourses().get(0).getId());
        return "redirect:/teacher";
    }
}
