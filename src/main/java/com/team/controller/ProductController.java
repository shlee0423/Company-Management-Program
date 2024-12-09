package com.team.controller;

import com.team.domain.EmployeeDTO;
import com.team.domain.ProductDTO;
import com.team.service.productservice.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@Log4j2
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/manage_product")
    public String getManageProduct(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String rental,
            @AuthenticationPrincipal EmployeeDTO employee,
            Model model
    ) {
        List<ProductDTO> products = productService.get_products(query, rental);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        for (ProductDTO product : products) {
            // 예약 수
            Integer reservationCount = productService.count_reservation(product.getProductNo());
            // 총수량 - 예약 수
            int availableAmount = product.getProductAmount() - reservationCount;
            // productAmount 에 set
            product.setProductAmount(availableAmount);
        }

        model.addAttribute("name",name);
        model.addAttribute("employee",employee);
        model.addAttribute("products", products);
        return "/product/manage_product";
    }

    @GetMapping("/register_product")
    public void getRegisterProduct() {
    }

    @PostMapping("/register_product")
    public String postRegisterProduct(ProductDTO product){
        productService.insert_product(product);
        log.info(product);
        return "redirect:/product/manage_product";
    }

    @GetMapping("/{productNo}")
    public String getProduct(
            @PathVariable("productNo") Integer productNo,
            Model model
    ){
        ProductDTO product = productService.select_product_by_no(productNo);
        model.addAttribute("product", product);
        return "product/read_product";
    }

    @PostMapping("/{productNo}")
    public String postUpdateProduct(
            @PathVariable("productNo") Integer productNo,
            ProductDTO product
    ){
        product.setProductNo(productNo);
        productService.update_product(product);
        return "redirect:/product/manage_product";
    }

    @ResponseBody
    @DeleteMapping("/manage_product")
    public void deleteProduct(
            @RequestBody List<ProductDTO> products
    ){
        for(ProductDTO product : products){
            System.out.println(product.getProductNo());
            productService.delete_product(product.getProductNo());
        }
    }


//    주소에 /1 , /2 와 같이 여러 개 있을 경우 사용
//    @GetMapping("/{product_no}")
//    public void product_no(){
//
//    }
}
