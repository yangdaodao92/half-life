package com.halflife.util;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class HtmlUnitUtil {

	public static WebClient instance = getWebClient(false);

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
		HtmlPage htmlPage;
		try {
			instance.getOptions().setJavaScriptEnabled(false);
			htmlPage = instance.getPage(url);
			return htmlPage.asXml();
		} catch (Exception e) {
			instance.getOptions().setJavaScriptEnabled(true);
			try {
				htmlPage = instance.getPage(url);
				return htmlPage.asXml();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	public static String getXml(String url, Boolean flag){
		HtmlPage htmlPage;
		try {
			instance.getOptions().setJavaScriptEnabled(flag);
			htmlPage = instance.getPage(url);
			return htmlPage.asXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
