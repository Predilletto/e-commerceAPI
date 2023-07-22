package com.prettyletto.ecommerceapi.service;

import com.prettyletto.ecommerceapi.model.Product;
import com.prettyletto.ecommerceapi.repository.ProductRepo;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.swing.text.html.Option;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepo productRepo;
  private ModelMapper modelMapper;

  @Transactional
  public Product save(Product product) {
    return productRepo.save(product);
  }

  public List<Product> list() {
    return productRepo.findAllByOrderByCreatedAtAsc();
  }

  public Optional<Product> find(UUID id) {
    return productRepo.findById(id);
  }

  public Optional<Product> update(UUID id, Product newProduct) {
    Optional<Product> existingProduct = productRepo.findById(id);
    if (existingProduct.isEmpty()) {
      return Optional.empty();
    }

    Product productToUpdate = existingProduct.get();
    newProduct.setId(productToUpdate.getId());
    newProduct.setCreatedAt(productToUpdate.getCreatedAt());
    modelMapper.map(newProduct, productToUpdate);
    Product updatedProduct = productRepo.save(productToUpdate);

    return Optional.of(updatedProduct);
  }

  @Transactional
  public boolean delete(UUID id) {
    Optional<Product> existingProduct = productRepo.findById(id);

    if (existingProduct.isEmpty()) {
      return false;
    }
    productRepo.deleteById(id);
    return true;
  }
}
