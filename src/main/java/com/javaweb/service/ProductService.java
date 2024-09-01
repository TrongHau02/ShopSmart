package com.javaweb.service;

import com.javaweb.domain.Product;
import com.javaweb.repository.FactoryRepository;
import com.javaweb.repository.ProductRepository;
import com.javaweb.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product handleSaveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProduct() {
        return this.productRepository.findAll();
    }

    public void handleDeleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public Product findProductById(long id) {
        return  this.productRepository.findProductById(id);
    }

    public Product getById(long id) {
        return this.productRepository.findById(id);
    }
}
