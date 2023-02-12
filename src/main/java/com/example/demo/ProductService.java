package com.example.demo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;
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
    static Collection<Product> LoadAllAvailableItems() throws SQLException {


        return null;
    }

    // 2. Vrátí informaci o produktu se zadaným ID
    public static int LoadProductByID(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int idLoadProduct = scanner.nextInt();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE id = '"+idLoadProduct+"' ");
        return idLoadProduct;
    }


    //3. PŘIDÁNÍ POLOŽKY

    public static Integer SaveItem(Product product) throws SQLException {
        Connection connection = null;
        Statement statement = connection.createStatement();
        statement.executeUpdate
                ("INSERT INTO product (id,partNumber, name, description, isForSale, price) " +
                        "VALUES ('"+product.getId()+"','"+product.partNumber+"','"+product.getName()+
                        "','"+product.getDescription()+"','"+product.getForSale()+"','"+product.getPrice()+"' )"+
                        Statement.RETURN_GENERATED_KEYS);
        return statement.getGeneratedKeys().getInt("id");
    }



    //4. AKTULIZACE ZADANÉ CENY PODLE ZADANÉHO ID
    public static void UpdatePriceById(int idChangePrice,int newPrice) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej ID produktu, kterému chceš změnit cenu: ");
        idChangePrice =sc.nextInt();
        System.out.println("Zadej jakou chceš nastavit novou cenu: ");
        newPrice= sc.nextInt();
        System.out.println("Zadal jsi ID"+ idChangePrice + "\nZadal jsi cenu:"+newPrice);
        String sql1 = "UPDATE product " + "SET price = '"+newPrice+"' WHERE id = '"+idChangePrice+"'";
        statement.executeUpdate(sql1);

    }

    //5. SMAZÁNÍ PODLE isForSle
    static void DeleteOutSaleItems() throws SQLException {

        String sql2 = "DELETE FROM product WHERE isForSale = '0' ";
        statement.executeUpdate(sql2);
    }



}


//  ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE id = '"+idLoadProduct+"' ");

