package com.example.trial.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.trial.Domain.MD_StoreVO;

public interface MD_StoreDAO extends MongoRepository<MD_StoreVO, String>{
	
	@Query("{category : ?0}")
	List<MD_StoreVO> findByCategory(String category);
	
	@Query("{type : ?0}")
	List<MD_StoreVO> findByType(String type);
}
