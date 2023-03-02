package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginResponse;
import com.app.pojos.Credentials;
import com.app.pojos.Customer;
import com.app.repository.CustomerRepo;

@CrossOrigin
@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtils;

	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/authenticate")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody Credentials cred) {
		System.out.println("Authenticating: " + cred);
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword());
			System.out.println("BEFORE: " + auth);
			auth = authManager.authenticate(auth);
			System.out.println("AFTER: " + auth);
			User user = (User) auth.getPrincipal();
			Customer customer = customerRepo.findByEmail(user.getUsername())
					.orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setId(customer.getId());
			loginResponse.setName(customer.getEmail());

			String token = jwtUtils.generateToken(user.getUsername());
			loginResponse.setToken(token);
			return ResponseEntity.ok(loginResponse);
		} catch (BadCredentialsException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
