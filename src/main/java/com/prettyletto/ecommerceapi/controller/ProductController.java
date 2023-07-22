package com.prettyletto.ecommerceapi.controller;

import com.prettyletto.ecommerceapi.model.Product;
import com.prettyletto.ecommerceapi.service.ProductService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.save(product);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProductsSortedByDate() {
    List<Product> products = productService.list();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Product> retrieveByID(@PathVariable UUID productId) {
    return productService
      .find(productId)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{productId}")
  public ResponseEntity<Product> updateProduct(
    @PathVariable UUID productId,
    @RequestBody Product product
  ) {
    return productService
      .update(productId, product)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable UUID productId) {
    if (productService.delete(productId)) {
      return ResponseEntity.ok(
        "Product with ID " + productId + " has been deleted"
      );
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
