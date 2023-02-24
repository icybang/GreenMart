package com.app.dto;

import com.app.pojos.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CartItemsDto {

	@JsonIgnoreProperties("vendor")
	private Product product;

	private int qty;
}
