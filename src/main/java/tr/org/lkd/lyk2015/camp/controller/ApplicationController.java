package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String getCourseCreate(@ModelAttribute("form") ApplicationFormDto applicationFormDto,Model model) {
		model.addAttribute("courseList",courseService.getAll());
        return "application/applicationForm";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String postAdminCreate(@ModelAttribute ApplicationFormDto applicationFormDto,Model model, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "application/applicationForm";
        }
        
        model.addAttribute("message","kaydınız başarılı");

        return "redirect:/application";
    }
	
	
	
	

}
