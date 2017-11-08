package com.halflife.controller;

import com.halflife.service.WebService;
import com.halflife.vo.ResultVo;
import com.halflife.vo.WebVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("web")
public class WebController {
	@Autowired
	private WebService webService;

	@RequestMapping
	public String init(Model model) {
		webService.init(model);
		return "/web/web";
	}

	@RequestMapping("add")
	public ResultVo add(@RequestBody WebVo webVo) {
		webService.add(webVo);
		return new ResultVo();
	}

	@RequestMapping("delete")
	public String delete() {
		return null;
	}

	@RequestMapping("update")
	public String update() {
		return null;
	}

}
