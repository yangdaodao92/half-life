package com.halflife.bean.web;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Web {

	private ObjectId id;
	private ObjectId webWrapperId;
	private String url;
	private String title;
	private String remark;
	private Integer level;
	private Integer status;
	private Date createAt;
	private Date updateAt;

}
