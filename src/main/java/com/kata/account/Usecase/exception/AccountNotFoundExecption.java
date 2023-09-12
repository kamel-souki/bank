package com.kata.account.Usecase.exception;

public class AccountNotFoundExecption extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AccountNotFoundExecption(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNotFoundExecption(String message) {
		super(message);
	}

	public AccountNotFoundExecption(Throwable cause) {
		super(cause);
	}

}
