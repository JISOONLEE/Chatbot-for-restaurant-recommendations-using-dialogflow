package com.example.trial.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.trial.Domain.GD_StoreVO;

public interface GD_StoreDAO extends MongoRepository<GD_StoreVO, String>{
	
	@Query("{category: ?0}")
	List<GD_StoreVO> findByCategory(String category);
	
	@Query("{type: ?0}")
	List<GD_StoreVO> findByType(@Param("type") String type);
}
