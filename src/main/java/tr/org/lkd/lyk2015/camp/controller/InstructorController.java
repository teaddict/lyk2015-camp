package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Instructor;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Instructor instructor) {
		
		return "instructorCreateForm";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute @Valid Instructor instructor, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
		{
			return "instructorCreateForm";
		}
		
		//todoService.create(todo);
		
		return "redirect:/instructors";
	}
	
}
