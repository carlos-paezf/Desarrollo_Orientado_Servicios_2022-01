package com.usta.p2t4_jwt.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.Product;
import com.usta.p2t4_jwt.repositories.IProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private IProductRepository _productRepository;

    public List<Product> getAllProducts() {
        return _productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return _productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return _productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return _productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        _productRepository.deleteById(id);
    }
}
