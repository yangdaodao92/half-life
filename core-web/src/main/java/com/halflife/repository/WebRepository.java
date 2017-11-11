package com.halflife.repository;

import com.halflife.bean.web.Web;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WebRepository extends MongoRepository<Web, String> {

	List<Web> findAllByWebWrapperIdAndIsValid(String webWrapperId, Boolean isValid);

}
