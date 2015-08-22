package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.controller.validator.ApplicationFormValidator;
import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.service.ApplicationService;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private ApplicationFormValidator applicationFormValidator;

	@Autowired
	private ApplicationService applicationService;

	@InitBinder
	private void initBinder(final WebDataBinder webDataBinder) {
		webDataBinder.addValidators(this.applicationFormValidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getCourseCreate(@ModelAttribute("form") ApplicationFormDto applicationFormDto, Model model,
			Authentication authentication) {

		Student student = null;
		if (authentication != null && authentication.getPrincipal() instanceof Student) {
			student = (Student) authentication.getPrincipal();

		}

		if (student != null) {
			// user is updating the form
			ApplicationFormDto formDto = this.applicationService.createApplicationDto(student.getId());
			model.addAttribute("form", formDto);
			model.addAttribute("update", true);

		} else {
			// new user creating a new application
			model.addAttribute("form", new ApplicationFormDto());

		}

		model.addAttribute("courseList", this.courseService.getAll());
		return "application/applicationForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postAdminCreate(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto,
			BindingResult bindingResult, Model model, Authentication authentication) {
		// bindingresult her zaman solundaki formun hatalarını alır
		model.addAttribute("courseList", this.courseService.getAll());
		if (bindingResult.hasErrors()) {
			return "application/applicationForm";
		} else {
			if (applicationFormDto.getApplication().getId() == null) {
				this.applicationService.create(applicationFormDto);
				model.addAttribute("message", "Başvurunuz başarıyla alındı, epostanızı kontrol ediniz.");
			} else {

				Student student = null;
				if (authentication != null && authentication.getPrincipal() instanceof Student) {
					student = (Student) authentication.getPrincipal();
				} else {
					return "error";
				}
				this.applicationService.isUserAuthorizedForForm(student, applicationFormDto.getApplication());

				this.applicationService.update(applicationFormDto);
				model.addAttribute("message", "Başvurunuz başarıyla gucellendi.");
			}
		}

		return "redirect:/application/success";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(Model model) {
		model.addAttribute("message", "Başvurunuz Başarıyla Kaydedildi");
		return "application/success";
	}

	@RequestMapping(value = "/validate/{id}", method = RequestMethod.GET)
	public String validate(@PathVariable("id") String id, Model model) {

		if (this.applicationService.validate(id)) {
			model.addAttribute("message", "Başvurunuz Başarıyla Kaydedildi.");
			return "application/validated";
		} else {
			model.addAttribute("message", "Böyle bir form bulunmamaktadır.");
			return "application/validated";
		}
	}

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public String selectApplication(@PathVariable("id") Long id, Model model) {

		this.applicationService.selectApp(id);
		return "redirect:/application/list";
	}

	@RequestMapping(value = "/reject/{id}", method = RequestMethod.GET)
	public String rejectApplication(@PathVariable("id") Long id, Model model) {

		this.applicationService.rejectApp(id);
		return "redirect:/application/liss";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("applications", this.applicationService.getAll());
		return "application/listApplication";
	}

}
