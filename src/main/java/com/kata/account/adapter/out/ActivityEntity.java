package com.kata.account.adapter.out;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="activity")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="activity_id")
	private Long id;
	
	@Column(name="account_id")
	private Long accountId;

	@Column
	private String transaction;

	@Column
	private Integer amount; 

	@Column(name="date_of_creation")
	private LocalDateTime timestamp;
}
