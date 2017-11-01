package com.halflife.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("simple")
public class SimpleController {

	@RequestMapping("hello")
	public String hello() {
		return "hello";
	}

}
