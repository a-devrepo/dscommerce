package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.entities.*;
import com.devsuperior.dscommerce.repository.OrderItemRepository;
import com.devsuperior.dscommerce.repository.OrderRepository;
import com.devsuperior.dscommerce.repository.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  @Autowired OrderRepository repository;
  @Autowired ProductRepository productRepository;
  @Autowired OrderItemRepository orderItemRepository;
  @Autowired UserService service;

  @Autowired AuthService authService;

  @Transactional(readOnly = true)
  public OrderDTO findById(Long id) {
    Order order =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    authService.validateSelfOrAdmin(order.getClient().getId());
    return new OrderDTO(order);
  }

  @Transactional
  public OrderDTO insert(OrderDTO dto) {
    Order order = new Order();
    order.setMoment(Instant.now());
    order.setStatus(OrderStatus.WAITING_PAYMENT);
    User user = service.authenticated();
    order.setClient(user);

    for (OrderItemDTO itemDTO : dto.getItems()) {
      Product product = productRepository.getReferenceById(itemDTO.getProductId());
      OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
      order.getItems().add(item);
    }
    repository.save(order);
    orderItemRepository.saveAll(order.getItems());
    return new OrderDTO(order);
  }
}
