package com.halflife.service;

import com.halflife.bean.web.Web;
import com.halflife.bean.web.WebWrapper;
import com.halflife.repository.WebRepository;
import com.halflife.repository.WebWrapperRepository;
import com.halflife.util.HtmlUnitUtil;
import com.halflife.vo.ResultVo;
import com.halflife.vo.WebVo;
import com.mongodb.WriteResult;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class WebService {
	@Autowired
	private WebWrapperRepository webWrapperRepository;
	@Autowired
	private WebRepository webRepository;
	@Autowired
	private MongoTemplate template;

	public void init(Model model) {
	}

	public List<WebWrapper> load() {
		List<WebWrapper> webWrapperList = webWrapperRepository.findAllByIsValid(true);
		for (WebWrapper webWrapper : webWrapperList) {
			webWrapper.setWebList(webRepository.findAllByWebWrapperIdAndIsValid(webWrapper.getId(), true));
		}
		return webWrapperList;
	}

	public WebWrapper add(WebVo webVo) {
		WebWrapper webWrapper = webVo.getWebWrapper();
		webWrapper.setIsValid(true);
		webWrapper.setCreateAt(new Date());
		webWrapperRepository.save(webWrapper);

		for (Web web : webVo.getWebs()) {
			saveNewWeb(web, webWrapper.getId());
		}
		return loadByWebWrapperId(webWrapper.getId());
	}

	public WebWrapper delete(String type, String id) {
		if ("web".equals(type)) {
			WriteResult writeResult = template.updateFirst(query(Criteria.where("id").is(id)), Update.update("isValid", false), Web.class);
			if (writeResult.isUpdateOfExisting()) {
				return loadByWebId(id);
			}
		}
		if ("webWrapper".equals(type)) {
			WriteResult writeResult = template.updateFirst(query(Criteria.where("id").is(id)), Update.update("isValid", false), WebWrapper.class);
			if (writeResult.isUpdateOfExisting()) {
				return loadByWebWrapperId(id);
			}
		}
		return null;
	}

	public WebWrapper update(WebVo webVo) {
		template.updateFirst(query(Criteria.where("id").is(webVo.getWebWrapper().getId())),
						Update.update("name", webVo.getWebWrapper().getName()), WebWrapper.class);
		for (Web web : webVo.getWebs()) {
			if (StringUtils.isNoneBlank(web.getId())) {
				Update update = new Update();
				update.set("url", web.getUrl());
				update.set("title", web.getTitle());
				template.updateFirst(query(Criteria.where("id").is(web.getId())), update, Web.class);
			} else {
				saveNewWeb(web, webVo.getWebWrapper().getId());
			}
		}
		return loadByWebWrapperId(webVo.getWebWrapper().getId());
	}

	private void saveNewWeb(Web web, String webWrapperId) {
		if (StringUtils.isBlank(web.getId())) {
			web.setId(null);
			web.setWebWrapperId(webWrapperId);
			if (StringUtils.isNoneBlank(web.getUrl()) && StringUtils.isBlank(web.getTitle())) {
				web.setTitle(getWebPageTitle(web.getUrl()));
			}
			web.setIsValid(true);
			web.setCreateAt(new Date());
			web.setStatus(0);
			webRepository.save(web);
		}
	}

	public String getWebPageTitle(String url) {
		if (StringUtils.isNoneBlank(url)) {
			String html = HtmlUnitUtil.getXml(url);
			if (StringUtils.isNoneBlank(html)) {
				Document document = Jsoup.parse(html);
				if (document != null) {
					Element element = document.select("title").first();
					return element.text();
				}
			}
		}
		return null;
	}

	/**
	 * 重新加载某一个网址集合
	 */
	public WebWrapper loadByWebId(String webId) {
		Web web = webRepository.findOne(webId);
		return loadByWebWrapperId(web.getWebWrapperId());
	}
	public WebWrapper loadByWebWrapperId(String webWrapperId) {
		WebWrapper webWrapper = webWrapperRepository.findOne(webWrapperId);
		if (webWrapper == null) {
			return null;
		}
		webWrapper.setWebList(webRepository.findAllByWebWrapperIdAndIsValid(webWrapper.getId(), true));
		return webWrapper;
	}

}
