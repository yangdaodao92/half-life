package com.halflife.controller;

import com.halflife.bean.User;
import com.halflife.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping
@SessionAttributes("userId")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping("login")
	public String login(User user, Model model) {
		User user1 = loginService.login(user);
		if (user1 != null) {
			model.addAttribute("userId", user1.getId());
			return "redirect:task/list";
		} else {
			model.addAttribute("user", user);
			return "login";
		}
	}

}
