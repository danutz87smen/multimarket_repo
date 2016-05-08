package com.dan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dan.entities.Account;
import com.dan.services.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/accounts/", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> getAllaccounts() {
		List<Account> accounts = accountService.getAccounts();
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> getAccountById(@PathVariable int id) {
		Account searchedAccount = null;
		searchedAccount = accountService.getById(id);
		if (searchedAccount == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(searchedAccount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/", method = RequestMethod.GET, params="userName")
	public ResponseEntity<Account> getAccountByUserName(@RequestParam String userName) {
		Account searchedAccount = null;
		searchedAccount = accountService.getByUserName(userName);
		if (searchedAccount == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(searchedAccount, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account account) {
		Account originalAccount = accountService.getById(id);
		if (originalAccount == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		originalAccount.setName(account.getName());
		accountService.updateAccount(originalAccount);
		return new ResponseEntity<Account>(originalAccount, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Account> deleteAccount(@PathVariable int id) {
		accountService.deleteAccountById(id);
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/accounts/", method = RequestMethod.POST)
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		accountService.createAccount(account);
		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}
}
