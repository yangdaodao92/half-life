package com.halflife.service;

import com.halflife.bean.web.Web;
import com.halflife.bean.web.WebWrapper;
import com.halflife.repository.WebRepository;
import com.halflife.repository.WebWrapperRepository;
import com.halflife.vo.WebVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
public class WebService {
	@Autowired
	private WebWrapperRepository webWrapperRepository;
	@Autowired
	private WebRepository webRepository;

	public void init(Model model) {
		List<WebWrapper> webWrapperList = webWrapperRepository.findAll();
		for (WebWrapper webWrapper : webWrapperList) {
			webWrapper.setWebList(webRepository.findAllByWebWrapperId(webWrapper.getId()));
		}
		model.addAttribute("webWrapperList", webWrapperList);
	}

	public void add(WebVo webVo) {
		WebWrapper webWrapper = webVo.getWebWrapper();
		webWrapper.setCreateAt(new Date());
		webWrapperRepository.save(webWrapper);

		for (Web web : webVo.getWebs()) {
			web.setWebWrapperId(webWrapper.getId());
			web.setCreateAt(new Date());
			web.setStatus(0);
			webRepository.save(web);
		}
	}


}
