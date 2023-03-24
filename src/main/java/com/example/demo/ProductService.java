package com.example.demo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service

public class ProductService {
    static Statement statement;

    static Connection connnection;

    static {
        try {
            connnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/products",
                    "root",
                    "Podhorkou234"
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductService() throws SQLException {
    }

    static {
        try {
            statement = connnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Product> loadAllAvailableItems() throws SQLException {
        statement = connnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            List<Product> actualProductList = new ArrayList<>();
            while (resultSet.next()){
                Product product = extractItemData(resultSet);
                actualProductList.add(product);
            }
        return actualProductList;
    }
    private static Product extractItemData(ResultSet resultSet)  throws SQLException{
        return new Product(
                resultSet.getInt("id"),
                resultSet.getInt("partNumber"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBoolean("isForSale"),
                resultSet.getBigDecimal("price"));
    }
       public Product loadProductByID(int id) throws SQLException {
           statement = connnection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE id = "+id+"");
           resultSet.next();
           return  extractItemData(resultSet);
       }

    public Product saveItem(Product product) throws SQLException {
        statement = connnection.createStatement();
        String sqlString = "INSERT INTO product(id,partNumber,name,description,isForSale,price) " +
                "VALUES ('"+product.getId()+"','"+product.getPartNumber()+"','"+product.getName()+"','"+product.getDescription()+"',"+product.getForSale()+","+product.getPrice()+")";
        statement.executeUpdate(sqlString , Statement.RETURN_GENERATED_KEYS);

        ResultSet generatedKeys = statement.getGeneratedKeys();
        product.setId(generatedKeys.getInt(1));

        return product;
    }

    public void updatePriceByID(int id, BigDecimal price)  throws SQLException {
        statement = connnection.createStatement();
        String sql1 = "UPDATE product SET price = "+price+" WHERE id = "+id+" ";
        statement.executeUpdate(sql1);
    }

    public void deleteOutSaleItems() throws SQLException {
        statement = connnection.createStatement();
        String sql2 = "DELETE FROM product WHERE isForSale = '0' ";
        statement.executeUpdate(sql2);
    }

}



