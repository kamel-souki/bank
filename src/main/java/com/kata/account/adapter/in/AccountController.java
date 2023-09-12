package com.kata.account.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kata.account.Usecase.OperationRequest;
import com.kata.account.Usecase.TransfertRequest;
import com.kata.account.Usecase.dto.AccountOperationResponse;
import com.kata.account.Usecase.dto.CustumerAccountActivitiesResponse;
import com.kata.account.Usecase.dto.CustumerAccountResponse;
import com.kata.account.domain.Account.AccountId;
import com.kata.account.domain.Customer.CustumerId;
import com.kata.account.service.ConsultAccountService;
import com.kata.account.service.ConsultActivityService;
import com.kata.account.service.SendMoneyService;
import com.kata.account.service.TransfertMoneyService;
import com.kata.account.service.WithdrawMoneyService;
import com.kata.account.domain.Money;

@RestController
public class AccountController {
	
	@Autowired
	SendMoneyService sendMoneyService;
	
	@Autowired
	WithdrawMoneyService withdrawMoneyService;
	
	@Autowired
	TransfertMoneyService transfertMoneyService;
	
	@Autowired
	ConsultAccountService consultAccountService;
	
	@Autowired
	ConsultActivityService consultActivityService;

	/**
	 * send money to the accountID with the desired amount 
	 * @return AccountOperationResponse with accountID, amount of the deposit and type of the transaction
	 */
	@PutMapping(path = "/accounts/send/{accountId}")
	public AccountOperationResponse sendMoney(@PathVariable("accountId") long accountId,
			@RequestParam("amount") int amount) {

		OperationRequest request = new OperationRequest(new AccountId(accountId), null, Money.of(amount));

		return sendMoneyService.sendMoney(request);

	}

	/**
	 * withdrawal money from the accountID with the desired amount 
	 * @return AccountOperationResponse object which contain accountID, amount of the withdraw and type of the transaction
	 */
	@PutMapping(path = "/accounts/withdraw/{accountId}")
	public AccountOperationResponse retrieveMoney(@PathVariable("accountId") long accountId,
			@RequestParam("amount") int amount) {

		OperationRequest request = new OperationRequest(new AccountId(accountId), null, Money.of(amount));

		return withdrawMoneyService.withdrawMoney(request);

	}
	
	/**
	 * Transfert money between my accounts
	 * @return AccountOperationResponse object which contain accountID, amount of the transfert and type of the transaction
	 */
	@PutMapping(path = "/accounts/transfert/{custumerId}")
	public AccountOperationResponse transfertMoney(@PathVariable("custumerId") long custumerId, @RequestParam("creditAccount") long creditAccount, 
			@RequestParam("debitAccount") long debitAccount, @RequestParam("amount") int amount) {

		TransfertRequest request = new TransfertRequest(new CustumerId(custumerId), new AccountId(creditAccount), new AccountId(debitAccount), Money.of(amount));

		return transfertMoneyService.transfertMoney(request);

	}

	/**
	 * get balance of the accountID 
	 * @return MoneyResponse object which contain accountID and its balance
	 */
	@GetMapping(path = "/accounts/{customerId}")
	public List<CustumerAccountResponse> consultAccount(@PathVariable("customerId") long customerId) {

		OperationRequest request = new OperationRequest(null, new CustumerId(customerId), null);

		return consultAccountService.getAccount(request);

	}

	/**
	 * get activities of all accounts 
	 * @return list of entity activities which contain all the details of the activities of my accounts
	 */
	@GetMapping(path = "/accounts/activities/{customerId}")
	public List<CustumerAccountActivitiesResponse> consultActivities(@PathVariable("customerId") long customerId) {

		OperationRequest request = new OperationRequest(null, new CustumerId(customerId), null);

		return consultActivityService.getActivities(request);
	}

}
