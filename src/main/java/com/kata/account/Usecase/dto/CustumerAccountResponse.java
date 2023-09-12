package com.kata.account.Usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustumerAccountResponse {
	
	private Long accountId;
	
	private String balance;

}
