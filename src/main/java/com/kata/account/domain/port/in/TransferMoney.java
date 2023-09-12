package com.kata.account.domain.port.in;

import com.kata.account.Usecase.TransfertRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;

public interface TransferMoney {

	public AccountOperationResponse transfertMoney(TransfertRequest operationRequest);
	
}
