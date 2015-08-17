package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	@RequestMapping(method = RequestMethod.GET)
    public String getCourseCreate(@ModelAttribute("form") ApplicationFormDto applicationFormDto) {

        return "application/applicationForm";
    }
	
	

}
