package edu.nmcpCS.myFirstWebApp.hello;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class SayHelloController {
	
	@RequestMapping("say-hello")
	public String sayHello(ModelMap model) {
		model.put("name", getCurrentUsername());
		return "sayHello";
	}
	
	private String getCurrentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
