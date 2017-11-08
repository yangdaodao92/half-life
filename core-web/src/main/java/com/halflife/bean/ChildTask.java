package com.halflife.bean;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class ChildTask {

	private ObjectId taskId;
	private Integer index;
	private String content;
	// 完成状态 0:未完成 10:完成 -1:已取消
	private Integer status = 0;
	// 完成时间
	private Date completeDate;

	private Date createAt = new Date();

}
