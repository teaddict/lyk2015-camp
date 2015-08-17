package tr.org.lkd.lyk2015.camp.controller;


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
import tr.org.lkd.lyk2015.camp.service.AdminService;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("")
	public String list(Model model) {
		List<Admin> admins = adminService.getAll();
		
		model.addAttribute("adminList", admins);
		return "admin/listAdmin";
	}
    

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getAdminCreate(@ModelAttribute Admin admin) {

        return "admin/createAdminForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postAdminCreate(@ModelAttribute @Valid Admin admin,@RequestParam(value= "passwordAgain") String passwordAgain, Model model, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin/createAdminForm";
        }
        
        if(!passwordAgain.equals(admin.getPassword()))
        {
        	model.addAttribute("message", "şifreler uyuşmuyor");
        	return "admin/createAdminForm";
        }

        adminService.create(admin);

        return "redirect:/admins";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getAdminUpdate(@PathVariable("id") Long id,Model model,@RequestParam (value="message",required=false) String message)
	{
		Admin admin= adminService.getById(id);
		model.addAttribute("admin", admin);
		model.addAttribute("message", message);
		return "admin/updateAdminForm";
	}
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String postAdminUpdate(@PathVariable("id") Long id, @ModelAttribute Admin admin, Model model) {

        adminService.update(admin);
        return "redirect:/admins";
    }
    
}