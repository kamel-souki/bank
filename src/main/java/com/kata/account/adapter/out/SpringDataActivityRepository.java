package com.kata.account.adapter.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataActivityRepository extends JpaRepository<ActivityEntity, Long>{
	
	@Query("select a from ActivityEntity a " +
			"where a.accountId = :accountId ")
	List<ActivityEntity> findByAccount(
			@Param("accountId") Long accountId);

}
