package com.halflife.repository;

import com.halflife.bean.web.WebWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WebWrapperRepository extends MongoRepository<WebWrapper, String> {

	List<WebWrapper> findAllByIsValid(Boolean isValid);

	WebWrapper findByIdAndIsValid(String id, Boolean isValid);

}
