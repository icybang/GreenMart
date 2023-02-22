package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.LoginResponse;
import com.app.pojos.Customer;
import com.app.repository.CustomerRepo;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public LoginResponse authenticateCustomer(String email, String pwd) {

		Customer customer = customerRepo.findByEmailAndPassword(email, pwd)
				.orElseThrow(() -> new RuntimeException("Auth Failed"));
		return new LoginResponse(customer.getId(),customer.getFirstName());
	}

}
