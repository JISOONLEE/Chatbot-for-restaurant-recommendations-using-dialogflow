package com.example.trial.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.trial.Domain.HD_StoreVO;

public interface HD_StoreDAO extends MongoRepository<HD_StoreVO, String>{
	
	@Query("{category : ?0}")
	List<HD_StoreVO> findByCategory(String category);
	
	@Query("{type : ?0}")
	List<HD_StoreVO> findByType(String type);
}
