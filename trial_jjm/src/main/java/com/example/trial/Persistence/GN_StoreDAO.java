package com.example.trial.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.trial.Domain.GN_StoreVO;

public interface GN_StoreDAO extends MongoRepository<GN_StoreVO, String>{
	
	@Query("{category: ?0}")
	List<GN_StoreVO> findByCategory(String category);
	
	@Query("{type: ?0}")
	List<GN_StoreVO> findByType(String type);
}
