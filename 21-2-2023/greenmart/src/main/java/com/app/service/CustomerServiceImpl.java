package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CustomerDto;
import com.app.dto.LoginResponse;
import com.app.dto.ProductDto;
import com.app.exceptions.ResourceNotFoundException;
import com.app.pojos.CartItems;
import com.app.pojos.Customer;
import com.app.pojos.Product;
import com.app.repository.CartItemRepo;
import com.app.repository.CustomerCartRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.ProductRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CustomerCartRepo cartRepo;

	@Autowired
	private CartItemRepo cartItemepo;

	@Override
	public LoginResponse authenticateCustomer(String email, String pwd) {

		Customer customer = customerRepo.findByEmailAndPassword(email, pwd)
				.orElseThrow(() -> new RuntimeException("Auth Failed"));
		return new LoginResponse(customer.getId(), customer.getFirstName());
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto, Long customerId) {
		Customer customerToBeUpdated = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", " id ", customerId));
		customerToBeUpdated.setFirstName(customerDto.getFirstName());
		customerToBeUpdated.setLastName(customerDto.getLastName());
		customerToBeUpdated.setEmail(customerDto.getEmail());
		customerToBeUpdated.setAddress(customerDto.getAddress());
		customerToBeUpdated.setMobNo(customerDto.getMobNo());

		return this.customerToCustomerDto(customerToBeUpdated);
	}

	private Customer dtoToCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setMobNo(customerDto.getMobNo());
		customer.setAddress(customerDto.getAddress());
		return customer;
	}

	private CustomerDto customerToCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPassword(customer.getPassword());
		customerDto.setMobNo(customer.getMobNo());
		customerDto.setAddress(customer.getAddress());
		return customerDto;
	}

	@Override
	public void deleteCustomer(Long customerId) {
		Customer customerToBeDeleted = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", " id ", customerId));

		this.customerRepo.delete(customerToBeDeleted);

	}

	@Override
	public List<CustomerDto> getAllUsers() {
		List<Customer> customers = this.customerRepo.findAll();
		List<CustomerDto> customerDtos = customers.stream().map(customer -> this.customerToCustomerDto(customer))
				.collect(Collectors.toList());
		return customerDtos;

	}

	@Override
	public CustomerDto getCustomerById(Long customerId) {
		Customer customer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", " id ", customerId));

		return this.customerToCustomerDto(customer);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepo.findAll();
		List<ProductDto> productdtos = products.stream().map(p -> this.ProductTodto(p)).collect(Collectors.toList());
		return productdtos;
	}

	private Product dtoToProduct(ProductDto productDto) {
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setRate(productDto.getRate());
		product.setDiscount(productDto.getDiscount());
		product.setProductDescription(productDto.getProductDescription());
		product.setImage(productDto.getImage());
		product.setExpiryDate(productDto.getExpiryDate());
		product.setProductQuantity(productDto.getProductQuantity());
		product.setAverageRating(productDto.getAverageRating());
		product.setAvailable(productDto.isAvailable());
		return product;
	}

	private ProductDto ProductTodto(Product product) {
		ProductDto productdto = new ProductDto();
		productdto.setProductName(product.getProductName());
		productdto.setRate(product.getRate());
		productdto.setDiscount(product.getDiscount());
		productdto.setProductDescription(product.getProductDescription());
		productdto.setImage(product.getImage());
		productdto.setExpiryDate(product.getExpiryDate());
		productdto.setProductQuantity(product.getProductQuantity());
		productdto.setAverageRating(product.getAverageRating());
		productdto.setAvailable(product.isAvailable());
		return productdto;
	}

//add method to add product in cartitem and add

	@Override
	public ProductDto addProductToCart(Long prodId, Long customerId, Integer qty) {
		Customer customer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", " id ", customerId));

		Product product = this.productRepo.findById(prodId)
				.orElseThrow(() -> new ResourceNotFoundException("product", " id ", customerId));

		CartItems cartItem = new CartItems();
		cartItem.setProduct(product);
		cartItem.setCart(customer.getCart());
		cartItem.setQty(qty);
		this.cartItemepo.save(cartItem);
		return this.ProductTodto(product);
	}

}
