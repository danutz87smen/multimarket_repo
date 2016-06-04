package com.dan.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.entities.Account;
import com.dan.repositories.IAccountRepository;
import com.dan.services.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private IAccountRepository repository;

	@Override
	public Account getById(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Account> getAccounts() {
		Iterator<Account> iterator = repository.findAll().iterator();
		List<Account> result = new ArrayList<>();
		while (iterator.hasNext()) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public void updateAccount(Account account) {
		repository.save(account);
	}

	@Override
	public Account createAccount(Account account) {
		return repository.save(account);
	}

	@Override
	public void deleteAccountById(long id) {
		repository.delete(id);
	}

	@Override
	public Account getByUserName(String userName) {
		return repository.findByUserName(userName);
	}
}
