package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.CourseService;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("")
	public String listInstructors(Model model) {
		List<Instructor> instructors = instructorService.getAll();
		
		model.addAttribute("instructorList", instructors);
		return "instructor/listInstructor";
	}
	
// INSTRUCTOR METHODS
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getInstructorCreate(@ModelAttribute Instructor instructor,Model model) {
    	
    	model.addAttribute("courseIds",courseService.getAll());
        return "instructor/createInstructorForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postInstructorCreate(@ModelAttribute @Valid Instructor instructor,@RequestParam(value= "passwordAgain") String passwordAgain, @RequestParam("courseIds") List<Long> ids, Model model, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "instructor/createInstructorForm";
        }
        
        if(!passwordAgain.equals(instructor.getPassword()))
        {
        	model.addAttribute("message", "şifreler uyuşmuyor");
        	return "instructor/createInstructorForm";
        }
        
        instructorService.create(instructor,ids);

        return "redirect:/instructors";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getAdminUpdate(@PathVariable("id") Long id,Model model,@RequestParam (value="message",required=false) String message)
	{
		Instructor instructor= instructorService.getById(id);
		model.addAttribute("instructor", instructor);
		model.addAttribute("courses",courseService.getAll());
		model.addAttribute("message", message);
		return "instructor/updateInstructorForm";
	}
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String postInstructornUpdate(@PathVariable("id") Long id, @ModelAttribute Instructor instructor, Model model) {

        instructorService.update(instructor);
        return "redirect:/instructors";
    }
	
}
