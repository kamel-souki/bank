package com.kata.account.adapter.out;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kata.account.domain.Account;
import com.kata.account.domain.Activity;
import com.kata.account.domain.Money;
import com.kata.account.domain.Transaction;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Activity.ActivityId;
import com.kata.account.domain.Customer.CustumerId;

@Component
public class AccountMapper {
	
	// create account from accountEntity and activityEntity
	public Account mapToAccount(AccountEntity accountEntity, List<ActivityEntity> activityEntity) {
		if(accountEntity != null && activityEntity != null) {
			return Account.withId(new AccountId(accountEntity.getId()), new CustumerId(accountEntity.getCustumerId()), new Money(accountEntity.getBalance()), mapToActivities(activityEntity));
		}
		return null;
	}
	
	// mapping list of activityEntity to list of activity
	public List<Activity> mapToActivities(List<ActivityEntity> activitiesEntity){
		if(activitiesEntity != null) {
			List<Activity> activities = new ArrayList<>();
			for(ActivityEntity activityEntity : activitiesEntity) {
				activities.add(new Activity(
						new ActivityId(activityEntity.getId()), 
						new AccountId(activityEntity.getAccountId()), 
						Transaction.valueOf(activityEntity.getTransaction()), 
						new Money(activityEntity.getAmount()), 
						activityEntity.getTimestamp()));
			}
			return activities;
		}
		return null;
	}
	
	// Mapping account to accountEntity
	public AccountEntity mapToAccountEntity(Account account) {
		if(account != null) {
			return new AccountEntity(account.getId().getValue(), account.getCustumerId().getValue(), account.getBalance().getAmount());
		}
		return null;
	}
	
	// Mapping activity to activityEntity
	public ActivityEntity mapToActivityEntity(Activity activity) {
		if(activity != null) {
			ActivityEntity activityEntity = new ActivityEntity(
					null, 
					activity.getAccountId().getValue(), 
					activity.getTransaction().toString(), 
					activity.getAmount().getAmount(), 
					activity.getTimestamp());
			return activityEntity;
		}
		return null;
	}
	
}
