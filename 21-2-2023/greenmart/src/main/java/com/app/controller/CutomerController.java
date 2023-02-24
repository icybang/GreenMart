package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CustomerDto;
import com.app.dto.LoginRequest;
import com.app.dto.ProductDto;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CutomerController {

	@Autowired
	private CustomerService customerService;

	public CutomerController() {
		System.out.println("inside " + getClass().getName());
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateCustomer(@RequestBody LoginRequest request) {
		System.out.println("in auth cust " + request);
		return ResponseEntity.ok(customerService.authenticateCustomer(request.getEmail(), request.getPassword()));
	}

	// PUT - update customer
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto,
			@PathVariable Long customerId) {
		CustomerDto updatedCustomer = this.customerService.updateCustomer(customerDto, customerId);
		return ResponseEntity.ok(updatedCustomer);
	}

	// DELETE - delete customer
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long customerId) {
		this.customerService.deleteCustomer(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("customer Deleted Successfully!!", true), HttpStatus.OK);
	}

	// GET - get customer
	@GetMapping("/")
	public ResponseEntity<List<CustomerDto>> getAllUser() {
		return ResponseEntity.ok(this.customerService.getAllUsers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getUserById(@PathVariable Long customerId) {
		return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
	}

	// GET - get all products
	@GetMapping("/product/")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return ResponseEntity.ok(this.customerService.getAllProducts());
	}

	// PUT - add product to cart

	@PutMapping("/cart/{customerId}/prodId/{prodId}/qty/{qty}")
	public ResponseEntity<ApiResponse> addProductToCart(@PathVariable Long prodId, @PathVariable Long customerId,
			@PathVariable Integer qty) {
		this.customerService.addProductToCart(prodId, customerId, qty);
		return new ResponseEntity<ApiResponse>(new ApiResponse("customer added product in cart Successfully!!", true),
				HttpStatus.OK);

	}

}
