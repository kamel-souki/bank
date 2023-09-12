package com.kata.account.Usecase;


import com.kata.account.domain.Money;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Customer.CustumerId;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransfertRequest {
	
	private CustumerId custumerId;
	
	private AccountId accountIdToCredit;
	
	private AccountId accountIdToDebit;
	
	private Money amount;
}
