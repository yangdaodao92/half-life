package com.halflife.repository;

import com.halflife.document.CurrentTask;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrentTaskRepository extends MongoRepository<CurrentTask, ObjectId> {


}
