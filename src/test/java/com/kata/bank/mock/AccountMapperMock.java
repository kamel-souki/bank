package com.kata.bank.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kata.account.adapter.out.AccountEntity;
import com.kata.account.adapter.out.ActivityEntity;
import com.kata.account.domain.Account;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Activity;
import com.kata.account.domain.Activity.ActivityId;
import com.kata.account.domain.Customer.CustumerId;
import com.kata.account.domain.Money;
import com.kata.account.domain.Transaction;

public class AccountMapperMock {

	public static Account configureAccount() {
		return Account.withId(new AccountId(1L), new CustumerId(1L), new Money(200), null);
	}
	
	public static Activity configureActivity() {
		return new Activity(new ActivityId(1L), new AccountId(2L), Transaction.WITHDRAWAL, new Money(200), LocalDateTime.now());
	}
	
	public static AccountEntity configureAccountEntity() {
		return new AccountEntity(1L, null, 0);
	}

	public static List<ActivityEntity> configureListActivitiesEntity() {
		ActivityEntity entity1 = new ActivityEntity(1L, 1L, "DEPOSIT", 200, LocalDateTime.now());
		ActivityEntity entity2 = new ActivityEntity(2L, 1L, "DEPOSIT", 300, LocalDateTime.now());
		 List<ActivityEntity> activities =  new ArrayList<>() {
			{
				add(entity1);
				add(entity2);
			}

		};
		return activities;
	}

}
