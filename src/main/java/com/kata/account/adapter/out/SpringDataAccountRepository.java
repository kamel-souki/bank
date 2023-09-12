package com.kata.account.adapter.out;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long>{
	
	@Query("select a from AccountEntity a " +
			"where a.custumerId = :custumerId ")
	List<AccountEntity> findByCustumer(
			@Param("custumerId") Long custumerId);

}
