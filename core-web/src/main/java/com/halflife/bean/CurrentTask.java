package com.halflife.bean;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class CurrentTask {

	private ObjectId currentTaskId;
	// 如果移动的是整个主任务，则childTaskIndexList这个字段为空
	private List<Integer> childTaskIndexList;

}
