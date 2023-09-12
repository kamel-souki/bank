package com.kata.account.Usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler {

	@ExceptionHandler(AccountNotFoundExecption.class)
	public ResponseEntity<AccountErrorResponse> handleEntityNotFound(AccountNotFoundExecption exc) {

		AccountErrorResponse error = new AccountErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

}
