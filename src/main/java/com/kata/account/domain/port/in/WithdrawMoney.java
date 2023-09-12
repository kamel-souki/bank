package com.kata.account.domain.port.in;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;

public interface WithdrawMoney {

	AccountOperationResponse withdrawMoney(OperationRequest operationRequest);
}
