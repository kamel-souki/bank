package com.kata.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.account.Usecase.TransfertRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;
import com.kata.account.Usecase.exception.AccountNotFoundExecption;
import com.kata.account.adapter.out.AccountPersistence;
import com.kata.account.domain.Account;
import com.kata.account.domain.Transaction;
import com.kata.account.domain.port.in.TransferMoney;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class TransfertMoneyService implements TransferMoney {

	public static final String LOG_WARN = "Trying to loading inexistant account ID {}";

	@Autowired
	private AccountPersistence accountPersistence;

	/**
	 * Service used to transfert money between my accounts
	 * @return AccountOperationResponse if operation succeed
	 * @throws AccountNotFoundException if account not found in DB
	 */
	@Override
	public AccountOperationResponse transfertMoney(TransfertRequest transfertRequest) {
		try {
			Account accountToDebit = accountPersistence.loadAccount(transfertRequest.getAccountIdToDebit());

			if (!accountToDebit.withdraw(transfertRequest.getAmount())) {
				return new AccountOperationResponse(accountToDebit.getId().getValue(), Transaction.FAIL,
						FormatService.convertAmount(transfertRequest.getAmount().getAmount()));
			}
			Account accountToCredit = accountPersistence.loadAccount(transfertRequest.getAccountIdToCredit());
			if (!accountToCredit.deposit(transfertRequest.getAmount())) {
				return new AccountOperationResponse(accountToCredit.getId().getValue(), Transaction.FAIL,
						FormatService.convertAmount(transfertRequest.getAmount().getAmount()));
			}
			accountPersistence.updateAccount(accountToDebit);
			accountPersistence.updateAccount(accountToCredit);
			return new AccountOperationResponse(accountToCredit.getId().getValue(), Transaction.TRANSFERT,
					FormatService.convertAmount(transfertRequest.getAmount().getAmount()));

		} catch (Exception e) {
			log.warn(LOG_WARN);
			throw new AccountNotFoundExecption("Account ID not found");
		}
	}

}
