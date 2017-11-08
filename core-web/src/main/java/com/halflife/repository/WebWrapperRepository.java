package com.halflife.repository;

import com.halflife.bean.web.WebWrapper;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebWrapperRepository extends MongoRepository<WebWrapper, ObjectId> {
}
