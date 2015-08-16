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

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("")
	public String listCourses(Model model) {
		List<Course> courses = courseService.getAll();
		
		model.addAttribute("courseList", courses);
		return "course/listCourse";
	}
	
// INSTRUCTOR METHODS
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCourseCreate(@ModelAttribute Course course) {

        return "course/createCourseForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postInstructorCreate(@ModelAttribute @Valid Course course, Model model, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "course/createCourseForm";
        }
        
        courseService.create(course);

        return "redirect:/courses";
    }
	
       
}

