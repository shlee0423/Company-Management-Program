package com.team.service.productservice;

import com.team.domain.ProductDTO;

import java.util.List;


public interface ProductService {
    void insert_product(ProductDTO product);
    List<ProductDTO> get_products(String query, String rental);
    ProductDTO select_product_by_no(Integer productNo);
    void update_product(ProductDTO product);
    void delete_product(Integer productNo);

    Integer count_reservation(Integer productNo);
}
