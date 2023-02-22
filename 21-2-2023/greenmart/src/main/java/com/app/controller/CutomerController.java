package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CutomerController {

	
	@Autowired
	private CustomerService customerService;
	
	public CutomerController() {
		System.out.println("inside "+getClass().getName());
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateCustomer(@RequestBody LoginRequest request) {
		System.out.println("in auth cust " + request);
		return ResponseEntity.ok(customerService.authenticateCustomer(request.getEmail(), request.getPassword()));
	}

	
	
}
