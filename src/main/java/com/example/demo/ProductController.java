package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

@RestController
public class ProductController {


    @GetMapping("/hello")      //FUNGUJE
    public String hello() {
        return "Aplikace druhého projetu ENGETO!";
    }

    @GetMapping("/allProduct")     //FUNGUJE
    public Collection <Product> loadAllAvailableItems()throws SQLException{
        return ProductService.LoadAllAvailableItems();
    }


   @GetMapping ("productID/{id}")
    public Product loadProductById (@PathVariable(value = "id")int id) throws SQLException{
        return ProductService.LoadProductByID(id);
}

    @PostMapping("/SaveProduct")
    public Product saveItem (@RequestBody Product product)throws SQLException{
        return ProductService.saveItem(product);
    }


    @PutMapping("/UpdateProduct/{id}/{price}")
    public void updatePriceById (@PathVariable("id") int id, @PathVariable("price") BigDecimal price)throws SQLException{
      ProductService.UpdatePriceById(id,price);

    }


    @DeleteMapping ("/DeleteProduct")     //FUNGUJE
    public void deleteOutOfSaleItem ()throws SQLException{
    ProductService.DeleteOutSaleItems();
}


}
