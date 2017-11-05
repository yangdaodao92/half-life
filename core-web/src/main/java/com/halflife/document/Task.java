package com.halflife.document;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.halflife.bean.ChildTask;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Task {

	private ObjectId id;
	private ObjectId createUserId;
	private Date createAt = new Date();

	// 任务时间
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date planDate;
	// 任务内容
	private String content;
	// 完成状态 0:未完成 10:完成 -1:已取消
	private Integer status = 0;
	// 任务类型 1:普通任务 2:长期跟进任务（需要显示在每天中）
	private Integer type;
	private List<ChildTask> childTaskList = new ArrayList<>();

}
