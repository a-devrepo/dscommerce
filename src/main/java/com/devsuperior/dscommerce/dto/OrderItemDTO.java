package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.OrderItem;

public class OrderItemDTO {
  private Long id;
  private String name;
  private Double price;
  private Integer quantity;

  public OrderItemDTO(Long id, String name, Double price, Integer quantity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public OrderItemDTO(OrderItem entity) {
    this.id = entity.getProduct().getId();
    this.name = entity.getProduct().getName();
    this.price = entity.getPrice();
    this.quantity = entity.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Double getSubTotal() {
    return price * quantity;
  }
}
