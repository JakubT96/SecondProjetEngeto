package com.example.demo;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
@Service

public class ProductService {

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
    static Statement statement;

    static {
        try {
            statement = connnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // 1.Vrací seznam všech druhů zboží v evidenci
    static Collection<Product> LoadAllAvailableItems() throws SQLException {    //FUNGUJE
        statement = connnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            List<Product> actualProductList = new ArrayList<>();
            while (resultSet.next()){
                Product product = extractItemData(resultSet);
                actualProductList.add(product);
            }
        return actualProductList;
    }
    private static Product extractItemData(ResultSet resultSet) throws SQLException {

        return new Product(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getBoolean(5),
                resultSet.getBigDecimal(6));
    }


    // 2. Vrátí informaci o produktu se zadaným ID
       public static Product LoadProductByID(int id) throws SQLException {
           statement = connnection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE id = "+id);
           resultSet.next();
           return extractItemData(resultSet);
       }


    //3. PŘIDÁNÍ POLOŽKY

    public static Product saveItem(Product product) throws SQLException {
        statement = connnection.createStatement();
        String sqlString =  "INSERT INTO products(id, partNumber,name,description,isForSale,price) " +
                "VALUES ('"+product.getId()+"','"+product.getPartNumber()+"','"+product.getName()+"',"+product.getDescription()+","
                +product.getForSale()+","+product.getPrice()+")";
        statement.executeUpdate(sqlString , Statement.RETURN_GENERATED_KEYS);

        ResultSet generatedKeys = statement.getGeneratedKeys();
        product.setId(generatedKeys.getInt(1));

        return product;
    }

    //4. AKTULIZACE ZADANÉ CENY PODLE ZADANÉHO ID
    public static void UpdatePriceByID(int id, BigDecimal price)  throws SQLException {
        statement = connnection.createStatement();
        String sql1 = "UPDATE products SET price="+price+" WHERE id="+id;
        statement.executeUpdate(sql1);
    }


    //5. SMAZÁNÍ PODLE isForSle
    static void DeleteOutSaleItems() throws SQLException {     //FUNGUJE
        statement = connnection.createStatement();
        String sql2 = "DELETE FROM product WHERE isForSale = '0' ";
        statement.executeUpdate(sql2);
    }

}



