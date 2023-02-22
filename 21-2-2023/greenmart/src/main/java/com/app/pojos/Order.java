package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order extends BaseEntity{

	@Column(name = "order_date")
	@CreationTimestamp
	private LocalDate orderDate;
	
	@Column(name = "delivery_date")
	private LocalDate deliveryDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "mode_payment")
	private ModeOfPayment modeOfPayment;

	@ManyToOne
	@JoinColumn(name = "cust_id", nullable = false)
	private Customer customer;
   
  
}
