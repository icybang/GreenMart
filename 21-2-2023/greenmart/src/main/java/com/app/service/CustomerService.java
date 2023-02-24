package com.app.service;

import java.util.List;

import com.app.dto.CustomerDto;
import com.app.dto.LoginResponse;
import com.app.dto.ProductDto;

public interface CustomerService {

	LoginResponse authenticateCustomer(String email, String pwd);

	CustomerDto updateCustomer(CustomerDto customerDto, Long CustomerId);

	void deleteCustomer(Long customerId);

	List<CustomerDto> getAllUsers();

	CustomerDto getCustomerById(Long customerId);

	List<ProductDto> getAllProducts();

	ProductDto addProductToCart(Long prodId, Long customerId, Integer qty);
}
