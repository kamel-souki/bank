package com.kata.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.CustumerAccountResponse;
import com.kata.account.Usecase.exception.AccountNotFoundExecption;
import com.kata.account.adapter.out.AccountPersistence;
import com.kata.account.domain.Account;
import com.kata.account.domain.port.in.ConsultAccount;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ConsultAccountService implements ConsultAccount {
	
	public static final String LOG_WARN = "Trying to loading inexistant account ID {}";

	@Autowired
	private AccountPersistence accountPersistence;


	/**
	 * Service used to consult account and their balance
	 * @return List<CustumerAccountResponse> if operation succeed
	 * @throws AccountNotFoundException if account not found in DB
	 */
	@Override
	public List<CustumerAccountResponse> getAccount(OperationRequest operationRequest) {

		try {
			List<Account> accounts = accountPersistence.loadAccountByCustumer(operationRequest.getCustumerId());
			List<CustumerAccountResponse> responses = new ArrayList<>();
			for (Account account : accounts) {
				CustumerAccountResponse response = new CustumerAccountResponse(account.getId().getValue(),
						FormatService.convertAmount(account.getBalance().getAmount()));
				responses.add(response);
			}
			return responses;
		} catch (Exception e) {
			log.warn(LOG_WARN + operationRequest.getAccountId().getValue());
			throw new AccountNotFoundExecption("Account ID not found : " + operationRequest.getAccountId().getValue());
		}

	}

}
