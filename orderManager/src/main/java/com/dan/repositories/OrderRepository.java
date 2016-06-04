package com.dan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dan.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	@Query("SELECT o FROM Order o WHERE o.accountId = :accountId")
	List<Order> findByAccountId(@Param("accountId")Long accountId);
}
