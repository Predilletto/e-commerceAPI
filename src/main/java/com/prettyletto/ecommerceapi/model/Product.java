package com.prettyletto.ecommerceapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank
  @Size(max = 255)
  private String name;

  @NotBlank
  @Size(max = 900)
  private String description;

  @DecimalMin(value = "0.01", message = "Price must be greater than 0")
  private double price;

  @NotNull
  @Min(value = 0, message = "Stock has to be at least greater than 0")
  private int stockQuantity;

  @NotNull
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }
}
