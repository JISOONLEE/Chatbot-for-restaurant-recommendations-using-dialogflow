package com.example.trial.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.trial.Domain.EJR_StoreVO;


public interface EJR_StoreDAO extends MongoRepository<EJR_StoreVO, String>{
	
	@Query("{category : ?0}")
	List<EJR_StoreVO> findByCategory(String category);
	
	@Query("{type : ?0}")
	List<EJR_StoreVO> findByType(String type);
}
