package tr.org.lkd.lyk2015.camp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.service.AdminService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getAdminCreate(@ModelAttribute Admin admin) {

        return "admin/createAdminForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postAdminCreate(@ModelAttribute @Valid Admin admin, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin/createAdminForm";
        }

        adminService.create(admin);

        return "redirect:/admins/list";
    }
}