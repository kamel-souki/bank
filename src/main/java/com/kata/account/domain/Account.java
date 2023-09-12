package com.kata.account.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.kata.account.domain.Customer.CustumerId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Account {

	private final AccountId id;
	
	private final CustumerId custumerId;

	private Money balance;

	private List<Activity> activities;


	/**
	 * Creates an entity with an ID. Use to retrieve a persisted entity.
	 */
	public static Account withId(AccountId accountId, CustumerId custumer, Money baselineBalance, List<Activity> activities) {
		return new Account(accountId, custumer, baselineBalance, activities);
	}

	/**
	 * Deposit amount of money to this account. 
	 * If successful, creates a new activity.
	 * @return true if the deposit was successful, false if not.
	 */
	public boolean deposit(Money money) {
		if (money.isNegatifOrZero())
			return false;
		balance.feedBalance(money);
		Activity deposit = new Activity(null, this.id, Transaction.DEPOSIT, money, LocalDateTime.now());
		this.activities.add(deposit);
		return true;
	}

	/**
	 * Withdraw the amount of money from this account. 
	 * If successful, creates a new activity.
	 * @return true if the withdrawal was successful, false if not.
	 */
	public boolean withdraw(Money money) {

		if (!checkWithdraw(money)) {
			return false;
		}

		balance.substractBalance(money);
		Activity withdrawal = new Activity(null, this.id, Transaction.WITHDRAWAL, money, LocalDateTime.now());
		this.activities.add(withdrawal);
		return true;
	}

	private boolean checkWithdraw(Money money) {
		if (balance.isNegatifOrZero())
			return false;
		else if (balance.isLessThan(money)) {
			return false;
		} else {
			return true;
		}
	}

	@Value
	public static class AccountId {
		private Long value;
	}
}
