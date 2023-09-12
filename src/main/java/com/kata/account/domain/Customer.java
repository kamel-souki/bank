package com.kata.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Customer {
	
	private final CustumerId custumerId;
	
	@Value
	public static class CustumerId {
		private Long value;
	}
}
