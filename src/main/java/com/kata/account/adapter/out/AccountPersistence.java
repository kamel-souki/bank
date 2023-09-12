package com.kata.account.adapter.out;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kata.account.domain.Account;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Activity;
import com.kata.account.domain.Customer.CustumerId;
import com.kata.account.domain.port.out.LoadAccountPort;
import com.kata.account.domain.port.out.LoadActivitiesPort;
import com.kata.account.domain.port.out.UpdateAccountPort;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AccountPersistence implements LoadAccountPort, UpdateAccountPort, LoadActivitiesPort {

	@Autowired
	private SpringDataAccountRepository accountRepository;

	@Autowired
	private SpringDataActivityRepository activityRepository;

	@Autowired
	private SpringDataCustumerRepository custumerRepository;

	@Autowired
	private AccountMapper accountMapper;

	/**
	 * restitute account from the id of persisted account and activities object
	 * if account exist in db call the map method 
	 * @return Account
	 * @throws EntityNotFoundException if account not found
	 */
	@Override
	public Account loadAccount(AccountId accountId) {

		log.info("Start loading account id : {}", accountId.getValue());
		AccountEntity accountEntity = accountRepository.findById(accountId.getValue())
				.orElseThrow(EntityNotFoundException::new);

		List<ActivityEntity> activities = activityRepository.findByAccount(accountId.getValue());

		Account account = accountMapper.mapToAccount(accountEntity, activities);

		return account;
	}

	/**
	 * restitute persisted account and activities object from the id of persisted customer id
	 * @return list of account
	 * @throws EntityNotFoundException if accounts not found
	 */
	@Override
	public List<Account> loadAccountByCustumer(CustumerId custumerId) {
		log.info("Start loading custumer id : {}", custumerId.getValue());
		custumerRepository.findById(custumerId.getValue()).orElseThrow(EntityNotFoundException::new);
		log.info("Start loading accounts by custumer id : {}", custumerId.getValue());
		List<AccountEntity> accountsEntity = accountRepository.findByCustumer(custumerId.getValue());
		List<Account> listAccount = new ArrayList<>();
		if (!accountsEntity.isEmpty()) {
			for (AccountEntity accountEntity : accountsEntity) {
				List<ActivityEntity> activities = activityRepository.findByAccount(accountEntity.getId());
				Account account = accountMapper.mapToAccount(accountEntity, activities);
				listAccount.add(account);
			}
		}
		return listAccount;
	}

	/**
	 * get persisted activities from accountID
	 * First check if account exist and then load activities 
	 * @return List<ActivityEntity>
	 * @throws EntityNotFoundException if account not found
	 */
	@Override
	public List<ActivityEntity> loadActivities(AccountId accountId) {

		log.info("Start loading account id : {}", accountId.getValue());
		accountRepository.findById(accountId.getValue()).orElseThrow(EntityNotFoundException::new);

		log.info("Start loading activities of account id : {}", accountId.getValue());
		return activityRepository.findByAccount(accountId.getValue());
	}

	/**
	 * update the balance of the target account in db
	 * insert new activities in activity table in db 
	 */
	@Override
	public void updateAccount(Account account) {

		log.info("Start updating account id : {}", account.getId().getValue());
		accountRepository.save(accountMapper.mapToAccountEntity(account));

		for (Activity activity : account.getActivities()) {
			if (activity.getId() == null) {
				log.info("Start saving activities of account ID : {}", account.getId().getValue());
				activityRepository.save(accountMapper.mapToActivityEntity(activity));
			}
		}
	}

}
