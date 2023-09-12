package com.kata.account.adapter.out;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kata.account.domain.Account;
import com.kata.account.domain.Activity;
import com.kata.bank.mock.AccountMapperMock;

@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

	@InjectMocks
	AccountMapper accountMapper;

	Account account;

	Activity activity;

	AccountEntity accountEntity;

	List<ActivityEntity> activitiesEntity;

	@BeforeEach
	void setUp() {
		account = AccountMapperMock.configureAccount();
		activity = AccountMapperMock.configureActivity();
		accountEntity = AccountMapperMock.configureAccountEntity();
		activitiesEntity = AccountMapperMock.configureListActivitiesEntity();
	}

	@Test
	void should_mapToAccount_return_null_when_activityEntity_is_null() {
		// when
		Account result = accountMapper.mapToAccount(null, activitiesEntity);

		// then
		assertThat(result).isNull();
	}

	@Test
	void should_mapToAccount_return_null_when_accountEntity_is_null() {
		// when
		Account result = accountMapper.mapToAccount(accountEntity, null);

		// then
		assertThat(result).isNull();
	}

	@Test
	void should_mapToAccount_return_account_when_data_is_valid() {
		// when
		Account result = accountMapper.mapToAccount(accountEntity, activitiesEntity);

		// then
		assertThat(result.getId().getValue()).isEqualTo(1L);
		assertThat(result.getBalance().getAmount()).isEqualTo(0);
		assertThat(result.getActivities().size()).isEqualTo(2);
	}

	@ParameterizedTest
	@NullSource
	void should_mapToActivities_return_null_when_activityEntity_is_null(List<ActivityEntity> activitiesEntity) {
		// when
		List<Activity> result = accountMapper.mapToActivities(activitiesEntity);

		// then
		assertThat(result).isNull();
	}

	@Test
	void should_mapToActivities_return_activities_when_activityEntity_isNot_null() {
		// when
		List<Activity> result = accountMapper.mapToActivities(activitiesEntity);

		// then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).getId().getValue()).isEqualTo(1L);
		assertThat(result.get(0).getAmount().getAmount()).isEqualTo(200);
		assertThat(result.get(0).getTransaction().toString()).isEqualTo("DEPOSIT");
	}

	@ParameterizedTest
	@NullSource
	void should_mapToAccountEntity_return_null_when_account_is_null(Account account) {
		// when
		AccountEntity result = accountMapper.mapToAccountEntity(account);

		// then
		assertThat(result).isNull();
	}

	@Test
	void should_mapToAccountEntity_return_accountEntity_when_account_isNot_null() {
		// when
		AccountEntity result = accountMapper.mapToAccountEntity(account);

		// then
		assertThat(result.getId()).isEqualTo(1L);
		assertThat(result.getBalance()).isEqualTo(200);
	}

	@ParameterizedTest
	@NullSource
	void should_mapToActivityEntity_return_null_when_activity_is_null(Activity activity) {
		// when
		ActivityEntity result = accountMapper.mapToActivityEntity(activity);

		// then
		assertThat(result).isNull();
	}

	@Test
	void should_mapToActivityEntity_return_activityEntity_when_activity_isNot_null() {
		// when
		ActivityEntity result = accountMapper.mapToActivityEntity(activity);

		// then
		assertThat(result.getAccountId()).isEqualTo(2L);
		assertThat(result.getTransaction()).isEqualTo("WITHDRAWAL");
	}
}
