package com.example.demo;
import SecondProjectEngeto.service.productService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

@RestController
public class ProductController {

    @GetMapping("/hello")
    public String hello() {
        return "Aplikace druhého projetu ENGETO!";
    }

    @GetMapping("/all-items")
    public Collection <Product> loadAllAvailableItems()throws SQLException{
        return productService.loadAllAvailableItems();
    }

    @GetMapping ("/product-ID/{id}")
    public Product loadProductById (@PathVariable(value ="id") int id) throws SQLException{
        return productService.loadProductByID(id);
    }

    @PostMapping("/save-item")
    public Product saveItem (@RequestBody Product product) throws SQLException{
        return productService.saveItem(product);
    }

    @PatchMapping("/update-product/{id}/{price}")
    public void updatePriceByID(@PathVariable(value = "id") int id,@PathVariable(value = "price") BigDecimal price) throws SQLException {
       productService.updatePriceByID(id, price);
    }

    @DeleteMapping ("/delete-item")
    public void deleteOutOfSaleItem ()throws SQLException{
    productService.deleteOutSaleItems();
}


}
