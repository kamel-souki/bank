package com.kata.account.domain.port.out;

import java.util.List;

import com.kata.account.domain.Account;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Customer;

public interface LoadAccountPort {
	
	Account loadAccount(AccountId accountId);
	
	List<Account> loadAccountByCustumer(Customer.CustumerId custumerId);

}
