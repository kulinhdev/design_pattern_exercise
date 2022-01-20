package com.bkap.manager;

import com.bkap.dao.CategoryDAOImp;
import com.bkap.dao.GeneralDao;
import com.bkap.dao.ProductDAOImp;
import com.bkap.entities.Category;
import com.bkap.entities.Product;
import com.bkap.factory.DAOFactory;

import java.util.List;
import java.util.Scanner;

// Má mày lười vừa thôi code điiii

public class CategoryManager {
    private Scanner scanner;

    public CategoryManager() {
        this.scanner = new Scanner(System.in);
    }

    public int Menu() {
        System.out.println("\n======== Category Manager ========\n");

        System.out.println("1. Display categories (A-Z)");
        System.out.println("2. Add new category");
        System.out.println("3. Update category");
        System.out.println("4. Delete category");
        System.out.println("5. Back");

        System.out.println("++++ Choose a option 1 to 5 ++++");

        int option = Integer.parseInt(scanner.nextLine());

        while (option < 1 || option > 5) {
            System.out.println("You need choose 1 to 5 !");

            option = Integer.parseInt(scanner.nextLine());
        }

        return option;
    }

    public void All() {
        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao cateDAO = DAOFactory.getDaoType(CategoryDAOImp.getInstance());

        List<Category> listCate = cateDAO.get();

        if (listCate != null) {
            System.out.println("---------------------------------");
            System.out.printf("|%-3s|%-20s|%-6s|\n", "Id", "Name", "Status");
            System.out.println("---------------------------------");
            listCate.stream().forEach(category -> category.DisPlayRow());
            System.out.println("---------------------------------");
        } else {
            System.out.println("Not found categories");
        }
    }

    public void Add() {
        System.out.println("\n=== Add New Category ===");
        boolean check;

        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao cateDAO = DAOFactory.getDaoType(CategoryDAOImp.getInstance());

        Category category = new Category();

        category.Input(cateDAO.listId());

        check = cateDAO.add(category);

        if (check) {
            System.out.println("Add new recodes successfully !");
        } else {
            System.err.println("Add new recodes failed ..!");
        }
    }

    public void Edit() {
        System.out.println("\n=== Edit Category ===");
        boolean check = true;

        All();

        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao cateDAO = DAOFactory.getDaoType(CategoryDAOImp.getInstance());

        // Check id exist
        String editId;
        List<String> listCateId = cateDAO.listId();

        System.out.println("Enter Id Edit: ");

        do {
            if(!check) {
                System.out.println("Id does not exits!");
            }

            check = false;

            editId = scanner.nextLine();
        } while (!listCateId.contains(editId));

        Category cateUpdate = (Category) cateDAO.findId(Integer.parseInt(editId));

        cateUpdate.Update();

        check = cateDAO.edit(cateUpdate);

        if (check) {
            System.out.println("Edit recodes successfully !");
        } else {
            System.err.println("Edit recodes failed ..!");
        }
    }

    public void Remove() {
        System.out.println("\n=== Remove Category ===");
        boolean check = true;

        All();

        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao cateDAO = DAOFactory.getDaoType(CategoryDAOImp.getInstance());

        GeneralDao proDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());

        List<String> listCateId = cateDAO.listId();

        List<Product> listProByCateId;

        String removeId;

        do {
            System.out.println("Enter Id Delete: ");

            if(!check) {
                System.out.println("The id does not exist or has a foreign key!");
            }

            check = false;

            removeId = scanner.nextLine();

            //  Check foreign key
            listProByCateId = proDAO.checkForeignKey(removeId);

        } while (!(listCateId.contains(removeId)) || (listProByCateId.size() > 0));

        Category cateRemove = (Category) cateDAO.findId(Integer.parseInt(removeId));

        check = cateDAO.remove(cateRemove);


        if (check) {
            System.out.println("Remove recodes successfully !");
        } else {
            System.err.println("Remove recodes failed ..!");
        }
    }

    public void MenuManager() {
        int option = 0;

        do {
            option = Menu();

            switch (option) {
                case 1:
                    All();
                    break;
                case 2:
                    Add();
                    break;
                case 3:
                    Edit();
                    break;
                case 4:
                    Remove();
                    break;
                default:
                    break;
            }
        }
        while (option > 0 && option < 5);
    }

}
