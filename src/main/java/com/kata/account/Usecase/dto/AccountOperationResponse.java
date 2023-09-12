package com.kata.account.Usecase.dto;

import com.kata.account.domain.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperationResponse {

	private Long accountId;

	private Transaction operation;

	private String amount;


}
