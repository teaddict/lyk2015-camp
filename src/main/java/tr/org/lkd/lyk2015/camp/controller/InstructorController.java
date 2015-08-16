package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@RequestMapping("")
	public String listInstructors(Model model) {
		List<Instructor> instructors = instructorService.getAll();
		
		model.addAttribute("instructorList", instructors);
		return "instructor/listInstructor";
	}
	
// INSTRUCTOR METHODS
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getInstructorCreate(@ModelAttribute Instructor instructor) {

        return "instructor/createInstructorForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postInstructorCreate(@ModelAttribute @Valid Instructor instructor,@RequestParam(value= "passwordAgain") String passwordAgain, Model model, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "instructor/createInstructorForm";
        }
        
        if(!passwordAgain.equals(instructor.getPassword()))
        {
        	model.addAttribute("message", "şifreler uyuşmuyor");
        	return "instructor/createInstructorForm";
        }
        
        instructorService.create(instructor);

        return "redirect:/instructors";
    }
	
}
