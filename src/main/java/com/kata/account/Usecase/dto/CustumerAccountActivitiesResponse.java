package com.kata.account.Usecase.dto;

import java.util.List;

import com.kata.account.adapter.out.ActivityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustumerAccountActivitiesResponse {
	
	private Long accountId;
	
	private String balance;
	
	private List<ActivityEntity> activities;

}
