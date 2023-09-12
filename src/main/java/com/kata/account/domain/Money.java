package com.kata.account.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class Money {
	
	public static final Integer ZERO = 0;
	
	@NonNull
	private Integer amount;
	
	public boolean isNegatifOrZero(){
		return this.amount.compareTo(ZERO) <= 0;
	}
	
	public boolean isLessThan(Money money){
		return this.amount.compareTo(money.amount) < 0;
	}
	
	public void substractBalance(Money money) {
		this.amount-=money.amount;
	}
	
	public void feedBalance(Money money) {
		this.amount+=money.amount;
	}
	
	public static Money of(Integer value) {
		return new Money(value);
	}

}
