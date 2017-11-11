package com.halflife.bean.web;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class WebWrapper {

	private String id;
	private String name;
	private Date createAt;
	private Boolean isValid;
	private List<Web> webList;

}
