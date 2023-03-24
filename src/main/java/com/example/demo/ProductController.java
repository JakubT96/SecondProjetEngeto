package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

@RestController
public class ProductController {
    ProductService productService;
    public ProductController (com.example.demo.ProductService productService) throws SQLException {
        ProductService = productService;
        this.productService = new ProductService();
    }
    private final ProductService ProductService;
    @GetMapping("/hello")
    public String hello() {
        return "Aplikace druhého projetu ENGETO!";
    }

    @GetMapping("/all-items")
    public Collection <Product> loadAllAvailableItems()throws SQLException{
        return ProductService.loadAllAvailableItems();
    }

    @GetMapping ("/product-ID/{id}")
    public Product loadProductById (@PathVariable(value ="id") int id) throws SQLException{
        return ProductService.loadProductByID(id);
    }

    @PostMapping("/save-item")
    public Product saveItem (@RequestBody Product product) throws SQLException{
        return ProductService.saveItem(product);
    }

    @PatchMapping("/update-product/{id}/{price}")
    public void updatePriceByID(@PathVariable(value = "id") int id,@PathVariable(value = "price") BigDecimal price) throws SQLException {
       ProductService.updatePriceByID(id, price);
    }

    @DeleteMapping ("/delete-item")
    public void deleteOutOfSaleItem ()throws SQLException{
    ProductService.deleteOutSaleItems();
}


}
