package edu.nmcpCS.myFirstWebApp.login;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;




@Controller
@SessionAttributes("name")
public class GreetController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String Greet(ModelMap model) {
		model.put("name", getCurrentUsername());
		return "sayHello";
	}
	
	private String getCurrentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
