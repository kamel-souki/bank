package com.kata.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;
import com.kata.account.Usecase.exception.AccountNotFoundExecption;
import com.kata.account.adapter.out.AccountPersistence;
import com.kata.account.domain.Account;
import com.kata.account.domain.Transaction;
import com.kata.account.domain.port.in.SendMoney;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class SendMoneyService implements SendMoney {

	public static final String LOG_WARN = "Trying to loading inexistant account ID {}";

	@Autowired
	private AccountPersistence accountPersistence;

	/**
	 * Service used to send money to the target account 
	 * @return AccountOperationResponse
	 * @throws AccountNotFoundException 
	 */
	@Override
	public AccountOperationResponse sendMoney(OperationRequest operationRequest) {

		try {
			Account account = accountPersistence.loadAccount(operationRequest.getAccountId());
			if (!account.deposit(operationRequest.getAmount())) {
				return new AccountOperationResponse(account.getId().getValue(), Transaction.FAIL,
						FormatService.convertAmount(operationRequest.getAmount().getAmount()));
			}
			accountPersistence.updateAccount(account);
			return new AccountOperationResponse(account.getId().getValue(), Transaction.DEPOSIT,
					FormatService.convertAmount(operationRequest.getAmount().getAmount()));
		} catch (Exception e) {
			log.warn(LOG_WARN + operationRequest.getAccountId().getValue());
			throw new AccountNotFoundExecption("Account ID not found : " + operationRequest.getAccountId().getValue());
		}

	}

}
