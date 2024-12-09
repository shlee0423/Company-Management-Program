package com.team.service.productservice;

import com.team.domain.ProductDTO;

import java.util.List;


public interface ProductService {
    void insertProduct(ProductDTO product);
    List<ProductDTO> getProducts(String query, String rental);
    ProductDTO selectProductByNo(Integer productNo);
    void updateProduct(ProductDTO product);
    void deleteProduct(Integer productNo);

    Integer countReservation(Integer productNo);
}
