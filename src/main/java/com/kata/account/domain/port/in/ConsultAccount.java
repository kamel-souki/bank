package com.kata.account.domain.port.in;

import java.util.List;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.CustumerAccountResponse;

public interface ConsultAccount {
	
	List<CustumerAccountResponse> getAccount(OperationRequest operationRequest);

}
