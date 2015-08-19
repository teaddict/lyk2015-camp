package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.controller.validator.ApplicationFormValidator;
import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private ApplicationFormValidator applicationFormValidator;

	@InitBinder
	private void initBinder(final WebDataBinder webDataBinder) {
		webDataBinder.addValidators(this.applicationFormValidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getCourseCreate(@ModelAttribute("form") ApplicationFormDto applicationFormDto, Model model) {
		model.addAttribute("courseList", this.courseService.getAll());
		return "application/applicationForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postAdminCreate(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto,
			BindingResult bindingResult, Model model) {
		// bindingresult her zaman solundaki formun hatalarını alır
		model.addAttribute("courseList", this.courseService.getAll());
		if (bindingResult.hasErrors()) {
			return "application/applicationForm";
		}
		model.addAttribute("message", "kaydınız başarılı");

		return "redirect:/application";
	}

}
