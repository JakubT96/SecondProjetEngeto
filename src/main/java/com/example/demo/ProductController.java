package com.example.demo;

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

    @GetMapping("/product")
    public Collection <Product> loadAllAvailableItems()throws SQLException{
        return ProductService.LoadAllAvailableItems();
    }


    @GetMapping ("productID/{partNumber}")
    public int loadProductById (@PathVariable("id")int id) throws SQLException{
        return ProductService.LoadProductByID(id);
}

    @PostMapping("/product")
    public Product saveItem (@RequestBody Product product)throws SQLException{
        Integer generatedId = ProductService.SaveItem(product);
        product.setId(generatedId);
        return product;
    }


    @PutMapping("/product/{id}/{price}")
    public void updatePriceById (@PathVariable("id") int idChangePrice, @PathVariable("price")int newPrice)throws SQLException{
        ProductService.UpdatePriceById(idChangePrice , newPrice);
    }


    @DeleteMapping ("/product")
    public void deleteOutOfSaleItem ()throws SQLException{
    ProductService.DeleteOutSaleItems();
}

}
