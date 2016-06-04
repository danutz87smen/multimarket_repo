package com.dan.services;

import java.util.List;

import com.dan.entities.Account;

public interface AccountService {
	Account getById(long Id);

	List<Account> getAccounts();

	void updateAccount(Account account);

	Account createAccount(Account account);

	void deleteAccountById(long id);

	Account getByUserName(String userName);
}
