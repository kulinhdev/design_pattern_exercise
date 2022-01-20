package com.bkap.dao;

import com.bkap.entities.Category;
import com.bkap.entities.Product;
import com.bkap.util.DataProvider;


// Má mày lười vừa thôi code điiii

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAOImp implements GeneralDao<Category> {
    private List<Category> data = new LinkedList<Category>();
    private static CategoryDAOImp instance;

    private CategoryDAOImp() {
    }

    public static CategoryDAOImp getInstance() {
        if (instance == null) {
            instance = new CategoryDAOImp();
        }
        return instance;
    }

    @Override
    public List<Category> get() {
        List<Category> categories = new LinkedList<Category>();

        String query = "{Call Sp_selectAllCategories()}";

        try {
            ResultSet result = DataProvider.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                boolean status = result.getBoolean("status");

                Category category = new Category(id, name, status);

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Category> getByName(String searchName) {
        String query = "{Call Sp_findCateByName(?)}"; // Name
        List<Category> categories = new LinkedList<Category>();

        try {
            ResultSet result = DataProvider.executeQuery(query, searchName);

            while (true) {
                if (!result.next()) break;
                int id = result.getInt("id");
                String name = result.getString("name");
                boolean status = result.getBoolean("status");

                Category category = new Category(id, name, status);

                categories.add(category);
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category findId(Object findId) {
        String query = "{Call Sp_findCategoryId(?)}"; // Id
        Category findCate = null;

        try {
            ResultSet result = DataProvider.executeQuery(query, findId);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                boolean status = result.getBoolean("status");

                findCate = new Category(id, name, status);
            }
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;

        return findCate;
    }

    @Override
    public boolean add(Category entity) {
        boolean check = false;

        String query = "{Call Sp_addCategory(?,?,?)}"; // Id Name, Status

        try {
            check = DataProvider.executeUpdate(query, entity.getId(), entity.getName(), entity.isStatus());
        } catch (Exception e) {
            System.out.println("Execute query failed please try again !");
        }

        return check;
    }

    @Override
    public boolean edit(Category entity) {
        boolean check = false;
        String query = "{Call Sp_editCategory(?,?,?)}";   // Id, Name, Status

        try {
            check = DataProvider.executeUpdate(query, entity.getId(), entity.getName(), entity.isStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public boolean remove(Category entity) {
        boolean check = false;
        String query = "{Call Sp_removeCategory(?)}";   // Id

        try {
            check = DataProvider.executeUpdate(query, entity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public List<String> listId() {
        String query = "{Call Sp_allIdCate()}";
        List<String> listId = new LinkedList<>();

        try {
            ResultSet result = DataProvider.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");

                listId.add(id + "");
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listId;
    }

    @Override
    public List<Product> checkForeignKey(String checkId) {
        return null;
    }


}
