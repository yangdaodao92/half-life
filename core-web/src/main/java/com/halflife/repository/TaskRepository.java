package com.halflife.repository;


import com.halflife.document.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, ObjectId> {

	List<Task> findByIdIn(List<ObjectId> ids);

}
