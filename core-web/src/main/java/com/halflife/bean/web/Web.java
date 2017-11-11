package com.halflife.bean.web;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Web {

	private String id;
	private String webWrapperId;
	private String url;
	private String title;
	private String remark;
	private Integer level;
	private Integer status;
	private Boolean isValid;
	private Date createAt;
	private Date updateAt;

}
