package com.bkap.entities;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Má mày lười vừa thôi code điiii

public class Category {
    private int id;
    private String name;
    private boolean status;

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void DisPlayRow() {
        System.out.printf("|%-3s|%-20s|%-6s|\n", this.id, this.name, this.status ? "Show" : "Hide");
    }

    public void InputId(List<String> listId) {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;

        do {
            try {
                isTrue = true;
                System.out.println("Enter Id Category: ");

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

                this.setId(Integer.parseInt(newId));
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
                } else if(newName.length() < 3 || newName.length() > 30) {
                    isTrue = false;
                    throw new InputMismatchException("Name must be 3-30 character!");
                }

                this.setName(newName);
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

    public void Input(List<String> listId) {
        InputId(listId);
        inputName();
        inputStatus();
    }

    public void Update() {
        inputName();
        inputStatus();
    }
}
