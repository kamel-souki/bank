package com.kata.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;
import com.kata.account.Usecase.exception.AccountNotFoundExecption;
import com.kata.account.adapter.out.AccountPersistence;
import com.kata.account.domain.Account;
import com.kata.account.domain.Transaction;
import com.kata.account.domain.port.in.WithdrawMoney;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class WithdrawMoneyService implements WithdrawMoney {

	public static final String LOG_WARN = "Trying to loading inexistant account ID {}";

	@Autowired
	private AccountPersistence accountPersistence;

	/**
	 * Service used to withdraw money from the target account
	 * @return accountresponse if operation succeed
	 * @throws AccountNotFoundException if account not found in DB
	 */
	@Override
	public AccountOperationResponse withdrawMoney(OperationRequest operationRequest) {
		try {
			Account account = accountPersistence.loadAccount(operationRequest.getAccountId());
			if (!account.withdraw(operationRequest.getAmount())) {
				return new AccountOperationResponse(account.getId().getValue(), Transaction.FAIL,
						FormatService.convertAmount(operationRequest.getAmount().getAmount()));
			}
			accountPersistence.updateAccount(account);
			return new AccountOperationResponse(account.getId().getValue(), Transaction.WITHDRAWAL,
					FormatService.convertAmount(operationRequest.getAmount().getAmount()));
		} catch (Exception e) {
			log.warn(LOG_WARN + operationRequest.getAccountId().getValue());
			throw new AccountNotFoundExecption("Account ID not found : " + operationRequest.getAccountId().getValue());
		}
	}
}
