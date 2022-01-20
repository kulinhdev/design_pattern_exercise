package com.bkap.entities;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Má mày lười vừa thôi code điiii

public class Product {
    private String id;
    private String name;
    private double price;
    private String categoryId;
    private boolean status;

    public Product(String id, String name, double price, String categoryId, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.status = status;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    // Input
    public void InputId(List<String> listId) {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;

        do {
            try {
                isTrue = true;
                System.out.println("Enter Id Product: ");

                String newId = sc.nextLine();
                if (newId.isEmpty()) {
                    isTrue = false;
                    throw new InputMismatchException("Id isn't empty!");
                } else if(Integer.parseInt(newId) < 0) {
                    isTrue = false;
                    throw new InputMismatchException("Id must be greater than 0!");
                } else if(listId.contains(newId)) {
                    isTrue = false;
                    throw new InputMismatchException("Id " + newId + " already exists!");
                }

                this.setId(newId);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (!isTrue);
    }

    // Enter name
    public void inputName() {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;

        do {
            try {
                isTrue = true;

                System.out.println("Enter Name: ");
                String newName = sc.nextLine();
                if (newName.isEmpty()) {
                    isTrue = false;
                    throw new InputMismatchException("Name isn't empty!");
                } else if(newName.length() < 6 || newName.length() > 30) {
                    isTrue = false;
                    throw new InputMismatchException("Name must be 6-30 character!");
                }

                this.setName(newName);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (!isTrue);
    }

    public void inputPrice() {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;

        do {
            try {
                isTrue = true;

                System.out.println("Enter Price: ");
                float price = Float.parseFloat(sc.nextLine());
                if (price < 1000) {
                    isTrue = false;
                    throw new InputMismatchException("Price min 1000!");
                }

                this.setPrice(price);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (!isTrue);
    }

    public void inputCategoryId(List<String> listId) {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;

        do {
            try {
                isTrue = true;

                System.out.println("Enter Category Id: ");
                String cateId = sc.nextLine();
                if (!listId.contains(cateId)) {
                    isTrue = false;
                    throw new InputMismatchException("Id category invalid!");
                }

                this.setCategoryId(cateId);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (!isTrue);
    }

    public void inputStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter status: ");
        String status = sc.nextLine();
        this.setStatus(Boolean.parseBoolean(status));
    }

    public void Input(List idProduct, List<String> idCategory) {
        InputId(idProduct);
        inputName();
        inputPrice();
        inputCategoryId(idCategory);
        inputStatus();
    }

    public void Update(List<String> idCategory) {
        inputName();
        inputPrice();
        inputCategoryId(idCategory);
        inputStatus();
    }

    public void DisPlayRow() {
        System.out.printf("|%-3s|%-15s|%-10.2f|%-15s|%-7s|\n", this.id, this.name, this.price, this.categoryId, this.status ? "Show" : "Hide");
    }

}
