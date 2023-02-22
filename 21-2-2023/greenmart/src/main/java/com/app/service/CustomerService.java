package com.app.service;

import com.app.dto.LoginResponse;

public interface CustomerService {

	
	LoginResponse authenticateCustomer(String email, String pwd);
}
