package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Order;
import com.app.pojos.OrderDetail;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetail, Long> {

	List<OrderDetail> findByOrder(Order order);
}
