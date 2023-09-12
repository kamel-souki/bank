package com.kata.account.Usecase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountErrorResponse {

	private Integer status;
	
	private String message;
	
	private Long timestamp;
}
