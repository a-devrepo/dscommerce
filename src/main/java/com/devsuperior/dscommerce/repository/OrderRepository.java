package com.devsuperior.dscommerce.repository;

import com.devsuperior.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
