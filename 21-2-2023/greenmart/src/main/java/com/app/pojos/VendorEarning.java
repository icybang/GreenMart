package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendor_earning")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VendorEarning {
	@CreationTimestamp
	private LocalDate date;

	@Column(precision = 10, scale = 2)
	private double amount;

	@OneToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

}
