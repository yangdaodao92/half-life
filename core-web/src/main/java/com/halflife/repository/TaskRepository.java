package com.halflife.repository;


import com.halflife.bean.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, ObjectId> {



}
