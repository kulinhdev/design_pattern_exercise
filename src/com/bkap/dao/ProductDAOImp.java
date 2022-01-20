package com.bkap.dao;

import com.bkap.entities.Product;
import com.bkap.util.DataProvider;

// Má mày lười vừa thôi code điiii

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAOImp implements GeneralDao<Product> {
    private List<Product> data = new LinkedList<Product>();
    private static ProductDAOImp instance;

    private ProductDAOImp() {}

    public static ProductDAOImp getInstance() {
        if (instance == null) {
            instance = new ProductDAOImp();
        }
        return instance;
    }

    @Override
    public List<Product> get() {
        List<Product> listProducts = new LinkedList<Product>();
        String query = "{Call Sp_selectAllProducts()}";

        try {
            ResultSet result = DataProvider.executeQuery(query);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                float price = result.getFloat("price");
                String categoryName = result.getString("CategoryName");
                boolean status = result.getBoolean("status");

                Product product = new Product(id, name, price, categoryName, status);

                listProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listProducts;
    }

    @Override
    public List<Product> getByName(String searchName) {
        List<Product> listProducts = new LinkedList<Product>();
        String query = "{Call Sp_findProductByName(?)}"; // Name

        try {
            ResultSet result = DataProvider.executeQuery(query, searchName);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                float price = result.getFloat("price");
                String categoryName = result.getString("CategoryName");
                boolean status = result.getBoolean("status");

                Product product = new Product(id, name, price, categoryName, status);

                listProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listProducts;
    }

    @Override
    public Product findId(Object findId) {
        Product product = null;
        String query = "{Call Sp_findProductId(?)}";

        try {
            ResultSet result = DataProvider.executeQuery(query, findId);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                float price = result.getFloat("price");
                String categoryId = result.getString("category_id");
                boolean status = result.getBoolean("status");

                product = new Product(id, name, price, categoryId, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public boolean add(Product entity) {
        String query = "{Call Sp_addProduct(?,?,?,?,?)}"; // Id, name, price, categoryId, status
        boolean check = false;

        try {
            check = DataProvider.executeUpdate(query, entity.getId(), entity.getName(), entity.getPrice(), Integer.parseInt(entity.getCategoryId()), entity.isStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public boolean edit(Product entity) {
        String query = "{Call Sp_editProduct(?,?,?,?,?)}"; // Id, name, price, categoryId, status
        boolean check = false;

        try {
            check = DataProvider.executeUpdate(query, entity.getId(), entity.getName(), entity.getPrice(), Integer.parseInt(entity.getCategoryId()), entity.isStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public boolean remove(Product entity) {
        String query = "{Call Sp_removeProduct(?)}"; // Id, name, price, categoryId, status
        boolean check = false;

        try {
            check = DataProvider.executeUpdate(query, entity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public List<String> listId() {
        String query = "{Call Sp_allIdProduct()}";
        List<String> listId = new LinkedList<>();

        try {
            ResultSet result = DataProvider.executeQuery(query);

            while (result.next()) {
                String id = result.getString("id");

                listId.add(id);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listId;
    }

    public List<Product> checkForeignKey(String checkId) {
        String query = "{Call Sp_getProductByCateId(?)}";
        List<Product> listPro = new LinkedList<>();

        try {
            ResultSet result = DataProvider.executeQuery(query, checkId);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                float price = result.getFloat("price");
                String categoryId = result.getString("category_id");
                boolean status = result.getBoolean("status");

                Product newProduct = new Product(id, name, price, categoryId, status);
                listPro.add(newProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listPro;
    }
}
