package com.halflife.bean;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Task {

	private ObjectId id;
	private ObjectId createUserId;
	private String content;
	private Integer status = 0;
	private Date createAt = new Date();
	private List<ChildTask> childTaskList;

}
