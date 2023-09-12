package com.kata.account.adapter.out;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.kata.account.domain.Account;
import com.kata.account.domain.Activity;
import com.kata.account.domain.Customer.CustumerId;
import com.kata.account.domain.Money;
import com.kata.account.domain.Transaction;
import com.kata.account.domain.Account.AccountId;

@DataJpaTest
@Import({ AccountPersistence.class, AccountMapper.class })
public class AccountPersistenceTest {

	@Autowired
	private AccountPersistence accountPersistence;

	@Autowired
	private SpringDataAccountRepository accountRepository;

	@Autowired
	private SpringDataActivityRepository activityRepository;

	@Test
	@Sql("account_persistence_test.sql")
	void testLoadAccount() {
		// when
		Account account = accountPersistence.loadAccount(new AccountId(2L));

		// then
		assertThat(account.getId().getValue()).isEqualTo(2L);
	}

	@Test
	@Sql("account_persistence_test.sql")
	void testLoadActivities() {
		// when
		List<ActivityEntity> result = accountPersistence.loadActivities(new AccountId(3L));

		// then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).getId()).isEqualTo(1L);
		assertThat(result.get(0).getAccountId()).isEqualTo(3L);
		assertThat(result.get(0).getTransaction()).isEqualTo("DEPOSIT");
		assertThat(result.get(0).getAmount()).isEqualTo(1000);
	}

	@Test
	void testUpdateAccount() {
		// Given
		Activity activity = new Activity(null, new AccountId(1L), Transaction.DEPOSIT, new Money(1000),
				LocalDateTime.now());
		List<Activity> activities = new ArrayList<>();
		activities.add(activity);
		Account account = Account.withId(new AccountId(1L), new CustumerId(1L), new Money(1500), activities);

		// when
		accountPersistence.updateAccount(account);

		// then
		assertThat(accountRepository.count()).isEqualTo(2);
		assertThat(activityRepository.count()).isEqualTo(1);
		assertThat(accountRepository.findById(1L).get().getId()).isEqualTo(1L);
		assertThat(accountRepository.findById(1L).get().getBalance()).isEqualTo(1500);
	}

}
