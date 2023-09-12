package com.kata.account.Usecase;


import com.kata.account.domain.Money;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Customer.CustumerId;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationRequest {
	
	private AccountId accountId;
	
	private CustumerId custumerId;
	
	private Money amount;
}
