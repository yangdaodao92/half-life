package com.halflife.repository;

import com.halflife.bean.web.Web;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WebRepository extends MongoRepository<Web, ObjectId> {

	List<Web> findAllByWebWrapperId(ObjectId webWrapperId);

}
