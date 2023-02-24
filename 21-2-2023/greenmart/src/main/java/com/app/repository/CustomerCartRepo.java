package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.CustomerCart;

public interface CustomerCartRepo extends JpaRepository<CustomerCart, Long> {

}
