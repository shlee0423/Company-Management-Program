package com.team.service.productservice;

import com.team.domain.ProductDTO;
import com.team.mapper.ProductMapper;
import com.team.mapper.ReserveMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ReserveMapper reserveMapper;
    @Override
    public void insertProduct(ProductDTO product) {
        productMapper.insertProduct(product);
    }

    @Override
    public List<ProductDTO> getProducts(String query, String rental) {
        return productMapper.selectProducts(query, rental);
    }

    @Override
    public ProductDTO selectProductByNo(Integer productNo) {
        return productMapper.selectProductByNo(productNo);
    }

    @Override
    public void updateProduct(ProductDTO product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProduct(Integer productNo) {
        productMapper.deleteProduct(productNo);
    }

    @Override
    public Integer countReservation(Integer productNo) {
        Integer count = reserveMapper.countReservation(productNo);
        System.out.println("제품번호: " + productNo + ", 승인수: " + count);
        return reserveMapper.countReservation(productNo);
    }
}
