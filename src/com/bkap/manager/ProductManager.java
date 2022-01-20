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

public class ProductManager {

    private Scanner scanner;
    private int count = 0;

    public ProductManager() {
        this.scanner = new Scanner(System.in);
    }

    public int Menu() {
        System.out.println("\n======== Product Manager ========\n");

        System.out.println("1. Display products (A-Z)");
        System.out.println("2. Add new product");
        System.out.println("3. Update product");
        System.out.println("4. Delete product");
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

        GeneralDao proDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());

        List<Product> listPro = proDAO.get();

        if (listPro != null) {
            System.out.println("-------------------------------------------------------");
            System.out.printf("|%-3s|%-15s|%-10s|%-15s|%-7s|\n", "Id", "Name", "Price", "Category", "Status");
            System.out.println("------------------------------------------------------");
            listPro.stream().forEach(product -> product.DisPlayRow());
            System.out.println("-------------------------------------------------------");

            System.out.println();

        } else {
            System.out.println("Not found any products");
        }
    }

    public void Add() {
        System.out.println("\n=== Add New Product ===");
        boolean check;

        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao proDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());

        GeneralDao cateDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());

        Product product = new Product();

        product.Input(proDAO.listId(), cateDAO.listId());

        check = proDAO.add(product);

        if (check) {
            System.out.println("Add new recodes successfully !");
        } else {
            System.err.println("Add new recodes failed ..!");
        }
    }

    public void Edit() {
        System.out.println("\n=== Edit Product ===");
        boolean check = true;

        All();

        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao proDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());
        GeneralDao cateDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());

        // Check id exist
        String editId;
        List<String> listProId = proDAO.listId();

        do {
            // Enter Id delete
            System.out.println("Enter Id Edit: ");

            if(!check) {
                System.out.println("Id does not exits!");
            }

            check = false;

            editId = scanner.nextLine();
        } while (!listProId.contains(editId));

        Product proUpdate = (Product) proDAO.findId(editId);

        proUpdate.Update(cateDAO.listId());

        check = proDAO.edit(proUpdate);

        if(check) {
            System.out.println("Edit recodes successfully !");
        } else {
            System.err.println("Edit recodes failed ..!");
        }
    }

    public void Remove() {
        System.out.println("\n=== Remove Product ===");
        boolean check = true;

        All();

        DAOFactory DAOFactory = new DAOFactory();

        GeneralDao proDAO = DAOFactory.getDaoType(ProductDAOImp.getInstance());

        // Check id exist
        String removeId;
        List<String> listProId = proDAO.listId();

        do {
            // Enter Id delete
            System.out.println("Enter Id Delete: ");

            if(!check) {
                System.out.println("Id does not exits!");
            }

            check = false;

            removeId = scanner.nextLine();
        } while (!listProId.contains(removeId));

        Product proRemove = (Product) proDAO.findId(removeId);

        check = proDAO.remove(proRemove);

        if(check) {
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
