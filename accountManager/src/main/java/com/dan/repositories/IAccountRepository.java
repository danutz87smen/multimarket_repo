package com.dan.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dan.entities.Account;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.userName = :userName")
	Account findByUserName(@Param("userName")String userName);
}
