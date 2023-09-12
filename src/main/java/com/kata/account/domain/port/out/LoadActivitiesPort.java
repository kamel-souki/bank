package com.kata.account.domain.port.out;

import java.util.List;

import com.kata.account.adapter.out.ActivityEntity;
import com.kata.account.domain.Account.AccountId;

public interface LoadActivitiesPort {
	
	List<ActivityEntity> loadActivities(AccountId accountId);
	
}
