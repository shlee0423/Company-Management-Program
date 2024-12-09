package com.team.mapper;

import com.team.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insertProduct(ProductDTO productDTO);

    List<ProductDTO> selectProducts(
            @Param("query") String query,
            @Param("rental") String rental
    );
    ProductDTO selectProductByNo(Integer productNo);

    void updateProduct(ProductDTO product);
    void deleteProduct(Integer productNo);
}
