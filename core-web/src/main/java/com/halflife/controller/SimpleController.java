package com.halflife.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class SimpleController {

	@RequestMapping()
	public String hello() {
		return "hello";
	}

}
