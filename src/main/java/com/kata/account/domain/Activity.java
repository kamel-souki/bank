package com.kata.account.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Getter
public class Activity {
	
	private final ActivityId id;
	
	@NonNull
	private final Account.AccountId accountId;
	
	private final Transaction transaction;
	
	private final Money amount;
	
	private final LocalDateTime timestamp;

	public Activity(
			ActivityId activityId,
			@NonNull Account.AccountId accountId,
			@NonNull Transaction transaction,
			@NonNull Money amount,
			@NonNull LocalDateTime timestamp) {
		this.id = activityId;
		this.accountId = accountId;
		this.transaction = transaction;
		this.amount = amount;
		this.timestamp = timestamp;
	}
	
	@Value
	public static class ActivityId {
		private Long value;
	}
}
