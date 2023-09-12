package com.kata.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.CustumerAccountActivitiesResponse;
import com.kata.account.Usecase.exception.AccountNotFoundExecption;
import com.kata.account.adapter.out.AccountPersistence;
import com.kata.account.adapter.out.ActivityEntity;
import com.kata.account.domain.Account;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ConsultActivityService implements com.kata.account.domain.port.in.ConsultActivity {
	
	public static final String LOG_WARN = "Trying to loading inexistant account ID {}";

	@Autowired
	private AccountPersistence accountPersistence;

	/**
	 * Service used to consult activities of all accounts 
	 * @return List<CustumerAccountActivitiesResponse> if operation succeed
	 * @throws AccountNotFoundException if account not found in DB
	 */
	@Override
	public List<CustumerAccountActivitiesResponse> getActivities(OperationRequest operationRequest) {

		try {
			List<Account> accounts = accountPersistence.loadAccountByCustumer(operationRequest.getCustumerId());
			List<CustumerAccountActivitiesResponse> listAccount = new ArrayList<>();
			for (Account account : accounts) {
				List<ActivityEntity> activities = accountPersistence.loadActivities(account.getId());
				CustumerAccountActivitiesResponse response = new CustumerAccountActivitiesResponse(
						account.getId().getValue(), FormatService.convertAmount(account.getBalance().getAmount()), activities);
				listAccount.add(response);
			}
			return listAccount;
		} catch (Exception e) {
			log.warn(LOG_WARN + operationRequest.getAccountId().getValue());
			throw new AccountNotFoundExecption("Account ID not found : " + operationRequest.getAccountId().getValue());
		}

	}

}
