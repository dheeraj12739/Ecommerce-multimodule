package com.microservices.productservice.service;

import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.entity.Product;
import com.microservices.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {

        Product product = new Product(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getProductId()
        );
        Product productSaved = productRepository.save(product);
        log.info("product saved with id {} ", productSaved.getId());
    }

    public List<ProductResponse> getProducts() {

        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponses = productList.stream().map(this::mapToProductResponse).toList();

        return productResponses;
    }

    private ProductResponse mapToProductResponse(Product product) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(productResponse.getDescription());
        productResponse.setPrice(productResponse.getPrice());

        return productResponse;
    }
}
