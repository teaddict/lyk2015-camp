package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test") // "/" urlinde çalışır
public class TestController {
	
	@RequestMapping("")
	public String test(Model model){
		model.addAttribute("message", "test başarılı!");
		return "test";
		//burda gidip html altında home.html a gidip render ediyor
		//bunu webconfig dosyasında templateresolver ile ayarladık
	}
}
