package com.kata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kata.account.adapter.out.AccountEntity;
import com.kata.account.adapter.out.CustumerEntity;
import com.kata.account.adapter.out.SpringDataAccountRepository;
import com.kata.account.adapter.out.SpringDataCustumerRepository;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(SpringDataAccountRepository repository, SpringDataCustumerRepository custumerRepository) {

		return args -> {
			CustumerEntity custumerEntity = custumerRepository.save(new CustumerEntity(null));
			repository.save(new AccountEntity(null, custumerEntity.getCustumerId(), 0));
			repository.save(new AccountEntity(null, custumerEntity.getCustumerId(), 0));
		};
	}
}
