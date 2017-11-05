package com.halflife.controller;

import com.halflife.document.User;
import com.halflife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangnx
 */
@RestController
@RequestMapping("simple")
public class SimpleController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("hello")
	public String hello() {
		return "吃俺老孙一棒！";
	}

	@RequestMapping("save")
	@ResponseBody
	public User save(User user) {
		userRepository.save(user);
		return user;
	}

}
