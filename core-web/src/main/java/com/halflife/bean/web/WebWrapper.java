package com.halflife.bean.web;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class WebWrapper {

	private ObjectId id;
	private String name;
	private Date createAt;
	private Boolean show;
	private List<Web> webList;

}
