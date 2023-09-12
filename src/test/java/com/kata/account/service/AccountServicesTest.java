package com.kata.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;
import com.kata.account.Usecase.dto.CustumerAccountActivitiesResponse;
import com.kata.account.Usecase.exception.AccountNotFoundExecption;
import com.kata.account.adapter.out.AccountPersistence;
import com.kata.account.domain.Account;
import com.kata.account.domain.Money;
import com.kata.account.domain.Transaction;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Customer.CustumerId;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AccountServicesTest {
	
	@InjectMocks
	SendMoneyService sendMoneyService;
	
	@InjectMocks
	WithdrawMoneyService withdrawMoneyService;
	
	@InjectMocks
	ConsultActivityService consultActivityService;

	@Mock
	AccountPersistence accountPersistence;

	@Mock
	Account account;

	OperationRequest operationRequest;

	@BeforeEach
	void setUp() {
		operationRequest = new OperationRequest(new AccountId(1L), new CustumerId(1L), new Money(1500));
	}

	@Test
	 void should_sendMoney_return_accountResponse_when_request_is_valid() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenReturn(account);
		when(account.deposit(eq(operationRequest.getAmount()))).thenReturn(true);
		when(account.getId()).thenReturn(operationRequest.getAccountId());
		
		// when
		AccountOperationResponse result = sendMoneyService.sendMoney(operationRequest);
		
		// then
		assertThat(result.getAccountId()).isEqualTo(1L);
		assertThat(result.getAmount()).isEqualTo("1500€");
		assertThat(result.getOperation()).isEqualTo(Transaction.DEPOSIT);
	}

	@Test
	 void should_sendMoney_return_transaction_fail_when_depositMoney_is_false() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenReturn(account);
		when(account.deposit(eq(operationRequest.getAmount()))).thenReturn(false);
		when(account.getId()).thenReturn(operationRequest.getAccountId());
		
		// when
		AccountOperationResponse result = sendMoneyService.sendMoney(operationRequest);
		
		// then
		assertThat(result.getAccountId()).isEqualTo(1L);
		assertThat(result.getAmount()).isEqualTo("1500€");
		assertThat(result.getOperation()).isEqualTo(Transaction.FAIL);
	}

	@Test
	 void should_sendMoney_throw_exception_when_account_not_found() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenThrow(new EntityNotFoundException());
		
		// when
		 Throwable thrown = catchThrowable(() -> sendMoneyService.sendMoney(operationRequest));
		
		// then
		 assertThat(thrown).isInstanceOf(AccountNotFoundExecption.class).hasMessage("Account ID not found : 1");
	}

	@Test
	 void should_withdrawMoney_return_accountResponse_when_request_is_valid() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenReturn(account);
		when(account.withdraw(eq(operationRequest.getAmount()))).thenReturn(true);
		when(account.getId()).thenReturn(operationRequest.getAccountId());
		
		// when
		AccountOperationResponse result = withdrawMoneyService.withdrawMoney(operationRequest);
		
		// then
		assertThat(result.getAccountId()).isEqualTo(1L);
		assertThat(result.getAmount()).isEqualTo("1500€");
		assertThat(result.getOperation()).isEqualTo(Transaction.WITHDRAWAL);
	}

	@Test
	 void should_withdrawMoney_return_transaction_fail_when_depositMoney_is_false() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenReturn(account);
		when(account.withdraw(eq(operationRequest.getAmount()))).thenReturn(false);
		when(account.getId()).thenReturn(operationRequest.getAccountId());
		
		// when
		AccountOperationResponse result = withdrawMoneyService.withdrawMoney(operationRequest);
		
		// then
		assertThat(result.getAccountId()).isEqualTo(1L);
		assertThat(result.getAmount()).isEqualTo("1500€");
		assertThat(result.getOperation()).isEqualTo(Transaction.FAIL);
	}

	@Test
	 void should_withdrawMoney_throw_exception_when_account_not_found() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenThrow(new EntityNotFoundException());
		
		// when
		 Throwable thrown = catchThrowable(() -> withdrawMoneyService.withdrawMoney(operationRequest));
		
		// then
		 assertThat(thrown).isInstanceOf(AccountNotFoundExecption.class).hasMessage("Account ID not found : 1");
	}

	@Test
	 void should_getMoney_return_accountOperationResponse_when_request_is_valid() {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenReturn(account);
		when(account.getId()).thenReturn(operationRequest.getAccountId());
		
		// when
		AccountOperationResponse result = sendMoneyService.sendMoney(operationRequest);
		
		// then
		assertThat(result.getAccountId()).isEqualTo(1L);
		assertThat(result.getAmount()).isEqualTo("1500€");
	}

	@ParameterizedTest
	@NullSource
	 void should_getMoney_throw_exception_when_request_isNot_valid(Account account) {
		// given
		when(accountPersistence.loadAccount(eq(operationRequest.getAccountId()))).thenReturn(account);
		
		// when
		Throwable thrown = catchThrowable(() -> sendMoneyService.sendMoney(operationRequest));
		
		// then
		assertThat(thrown).isInstanceOf(AccountNotFoundExecption.class).hasMessage("Account ID not found : 1");
	}

	@Test
	void should_getActivities_return_entityActivities_when_request_is_valid() {
		// when
		List<CustumerAccountActivitiesResponse> result = consultActivityService.getActivities(operationRequest);

		// then
		assertThat(result.size()).isEqualTo(0);
	}

}
