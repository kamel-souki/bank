package com.kata.account.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kata.account.domain.Account.AccountId;

public class AccountTest {

	Account account;

	@BeforeEach
	 void setUp() {

		account = Account.withId(new AccountId(5L), null, new Money(0), new ArrayList<>());
	}

	@Test
	 void should_deposit_return_true_when_deposit_is_done() {
		// when
		boolean result = account.deposit(Money.of(150));

		// then
		assertThat(result).isTrue();
		assertThat(account.getBalance().getAmount()).isEqualTo(150);
		assertThat(account.getActivities().get(0).getAccountId().getValue()).isEqualTo(5L);
		assertThat(account.getActivities().get(0).getTransaction()).isEqualTo(Transaction.DEPOSIT);
	}

	@Test
	 void should_withdraw_return_true_when_withdraw_is_done() {
		// given
		account.deposit(Money.of(150));

		// when
		boolean result = account.withdraw(Money.of(50));

		// then
		assertThat(result).isTrue();
		assertThat(account.getBalance().getAmount()).isEqualTo(100);
		assertThat(account.getActivities().get(0).getAccountId().getValue()).isEqualTo(5L);
		assertThat(account.getActivities().get(1).getTransaction()).isEqualTo(Transaction.WITHDRAWAL);
	}

	@Test
	 void should_withdraw_return_false_when_balance_is_zero() {
		// when
		boolean result = account.withdraw(Money.of(50));

		// then
		assertThat(result).isFalse();
		assertThat(account.getBalance().getAmount()).isEqualTo(0);
	}

	@Test
	 void should_withdraw_return_false_when_amount_is_bigger_then_balance() {
		// given
		account.deposit(Money.of(150));

		// when
		boolean result = account.withdraw(Money.of(200));

		// then
		assertThat(result).isFalse();
		assertThat(account.getBalance().getAmount()).isEqualTo(150);

	}

}
