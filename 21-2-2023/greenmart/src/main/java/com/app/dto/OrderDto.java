package com.app.dto;

import java.time.LocalDate;

import com.app.pojos.BaseEntity;
import com.app.pojos.Customer;
import com.app.pojos.ModeOfPayment;
import com.app.pojos.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto extends BaseEntity {

	private LocalDate orderDate;

	private LocalDate deliveryDate;

	private OrderStatus orderStatus;

	private ModeOfPayment modeOfPayment;

	@JsonIgnore
	private Customer customer;

	public OrderDto(LocalDate orderDate, LocalDate deliveryDate, OrderStatus orderStatus, ModeOfPayment modeOfPayment) {
		super();
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orderStatus = orderStatus;
		this.modeOfPayment = modeOfPayment;
	}

}
