package com.halflife.vo;

import com.halflife.bean.web.Web;
import com.halflife.bean.web.WebWrapper;
import lombok.Data;

import java.util.List;

@Data
public class WebVo {

	private WebWrapper webWrapper;
	private List<Web> webs;

}
