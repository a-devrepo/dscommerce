package com.devsuperior.dscommerce.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {
  @Serial
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String authority;

  public Role() {}

  public Role(Long id, String authority) {
    this.id = id;
    this.authority = authority;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }
}
