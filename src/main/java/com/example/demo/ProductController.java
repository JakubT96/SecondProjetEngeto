package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
public class ProductController {

    @GetMapping("/hello")
    public String hello() {
        return "Aplikace druhého projetu ENGETO!";
    }

    @GetMapping("/allProduct")
    public Collection <Product> loadAllAvailableItems()throws SQLException{
        return ProductService.LoadAllAvailableItems();
    }

    @GetMapping ("/productID/{id}")
    public Product loadProductById (@PathVariable(value ="id") int id) throws SQLException{
        return ProductService.LoadProductByID(id);
    }

    @PostMapping("/SaveProduct")
    public Product saveItem (@RequestBody Product product) throws SQLException{
        return ProductService.saveItem(product);
    }

    @PostMapping("/UpdateProduct/{id}/{price}")
    public void updatePriceByID(@PathVariable(value = "id") int id,@PathVariable(value = "price") BigDecimal price) throws SQLException {
       ProductService.UpdatePriceByID(id, price);
    }

    @DeleteMapping ("/DeleteProduct")
    public void deleteOutOfSaleItem ()throws SQLException{
    ProductService.DeleteOutSaleItems();
}


}
