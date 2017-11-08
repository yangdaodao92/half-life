package com.halflife.util;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitUtil {

	public static WebClient getWebClient() {
		return getWebClient(true);
	}

	public static WebClient getWebClient(boolean flag) {
		WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
		//支持https
		webClient.getOptions().setUseInsecureSSL(true);
		// JS解释器启用，默认为true
		webClient.getOptions().setJavaScriptEnabled(flag);
		// 禁用css支持
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
		webClient.getOptions().setTimeout(50000);
		//设置js运行超时时间
		webClient.setJavaScriptTimeout(8000);
		//设置页面等待js响应时间
		webClient.waitForBackgroundJavaScript(500);
		// js运行错误时，是否抛出异常
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		return webClient;
	}

	public static String getXml(WebClient webClient, String url){
		return getXml(webClient, url, true);
	}

	public static String getXml(WebClient webClient, String url, boolean flag){
		HtmlPage htmlPage;
		webClient.getOptions().setJavaScriptEnabled(flag);
		try {
			htmlPage = webClient.getPage(url);
			return htmlPage.asXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getXml(String url){
		WebClient webClient = getWebClient();
		HtmlPage htmlPage;
		webClient.getOptions().setJavaScriptEnabled(false);
		try {
			htmlPage = webClient.getPage(url);
			return htmlPage.asXml();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	/**
	 * 使用一个新的webClient
	 * @param url
	 * @return
	 */
	public static String getXml(String url, boolean flag){
		WebClient webClient = getWebClient();
		HtmlPage htmlPage;
		webClient.getOptions().setJavaScriptEnabled(flag);
		try {
			htmlPage = webClient.getPage(url);
			return htmlPage.asXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

//	String url = "http://mp.weixin.qq.com/s?__biz=MzI2OTU3MDI3NQ==&mid=2247483989&idx=1&sn=552a88aa1341da761810fdff8e1fd043&chksm=eadf1bd9dda892cf9d69c80cb24b08eebbc5dab022d53ce94b7549d1bbc4c398fae95249dcf6&mpshare=1&scene=1&srcid=1106GXsgJKsBrdyoPZ6X3GEn#rd";
//
//	Document document = Jsoup.parse(HtmlUnitUtil.getXml(url, true));
//	Element element = document.select("title").first();
//		System.out.println(element.text());

}
