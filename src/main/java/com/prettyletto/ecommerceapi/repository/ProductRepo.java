package com.prettyletto.ecommerceapi.repository;

import com.prettyletto.ecommerceapi.model.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
  List<Product> findAllByOrderByCreatedAtAsc();
}
