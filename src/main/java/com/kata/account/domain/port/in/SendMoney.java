package com.kata.account.domain.port.in;


import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;

public interface SendMoney {

	AccountOperationResponse sendMoney(OperationRequest operationRequest);
	
}
