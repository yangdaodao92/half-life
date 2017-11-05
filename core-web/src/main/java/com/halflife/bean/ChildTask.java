package com.halflife.bean;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class ChildTask {

	private ObjectId taskId;
	private Integer index;
	private String content;
	private Integer status = 0;

	private Date createAt = new Date();

}
