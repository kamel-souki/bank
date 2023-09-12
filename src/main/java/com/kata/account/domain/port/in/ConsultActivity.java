package com.kata.account.domain.port.in;

import java.util.List;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.dto.CustumerAccountActivitiesResponse;

public interface ConsultActivity {
	
	List<CustumerAccountActivitiesResponse> getActivities(OperationRequest operationRequest);

}
