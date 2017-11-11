package com.halflife.controller;

import com.halflife.bean.web.WebWrapper;
import com.halflife.service.WebService;
import com.halflife.vo.ResultVo;
import com.halflife.vo.WebVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("web")
public class WebController {
	@Autowired
	private WebService webService;

	@RequestMapping
	public String init(Model model) {
		return "/web/web";
	}

	@RequestMapping("load")
	@ResponseBody
	public List<WebWrapper> load(Model model) {
		return webService.load();
	}

	@RequestMapping("add")
	@ResponseBody
	public WebWrapper add(@RequestBody WebVo webVo) {
		return webService.add(webVo);
	}

	@RequestMapping("delete/{type}/{id}")
	@ResponseBody
	public WebWrapper delete(@PathVariable String type, @PathVariable String id) {
		return webService.delete(type, id);
	}

	@RequestMapping("update")
	@ResponseBody
	public WebWrapper update(@RequestBody WebVo webVo) {
		return webService.update(webVo);
	}

	@RequestMapping("loadByWebWrapperId")
	@ResponseBody
	public WebWrapper loadByWebWrapperId(String webWrapperId) {
		return webService.loadByWebWrapperId(webWrapperId);
	}

	@RequestMapping("getWebPageTitle")
	@ResponseBody
	public String getWebPageTitle(String url) {
		return webService.getWebPageTitle(url);
	}

}
