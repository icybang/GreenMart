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
import com.app.dto.CartItemsDto;
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

	// PUT - add product to cartitems

	@PutMapping("/cart/{customerId}/prodId/{prodId}/qty/{qty}")
	public ResponseEntity<ApiResponse> addProductToCart(@PathVariable Long prodId, @PathVariable Long customerId,
			@PathVariable Integer qty) {
		this.customerService.addProductToCart(prodId, customerId, qty);
		return new ResponseEntity<ApiResponse>(new ApiResponse("customer added product in cart Successfully!!", true),
				HttpStatus.OK);

	}

	// Get - view cart
	@GetMapping("/cart/{customerId}")
	public ResponseEntity<List<CartItemsDto>> showCustomerCart(@PathVariable Long customerId) {
		return ResponseEntity.ok(this.customerService.getCustomerCart(customerId));
	}

	// PUT - update cart
	@PutMapping("/cart/{cartItemId}/qty/{qty}")
	public ResponseEntity<ApiResponse> updateCustomerCart(@PathVariable Long cartItemId, @PathVariable Integer qty) {
		this.customerService.updateCustomerCart(cartItemId, qty);
		return new ResponseEntity<ApiResponse>(new ApiResponse("customer cart updated  Successfully!!", true),
				HttpStatus.OK);

	}

	// GET - getProduct by category
	@GetMapping("/product/{categoryId}")
	public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Long categoryId) {
		return ResponseEntity.ok(this.customerService.getProduct(categoryId));
	}

	// GET - getProduct by rate
	@GetMapping("/product/rate/{rate}")
	public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable double rate) {
		return ResponseEntity.ok(this.customerService.getProduct(rate));
	}

	// GET - getProduct by rate
	@GetMapping("/product/name/{name}")
	public ResponseEntity<List<ProductDto>> getProductByName(@PathVariable String name) {
		return ResponseEntity.ok(this.customerService.getProductByName(name));
	}

}
