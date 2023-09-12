package com.kata.account.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="custumer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustumerEntity {

	@Id
	@Column(name="custumer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custumerId;
	
	
}
