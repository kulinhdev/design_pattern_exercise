package com.bkap.program;

import com.bkap.entities.Product;
import com.bkap.manager.CategoryManager;
import com.bkap.manager.ProductManager;

import java.util.Scanner;

// Má mày lười vừa thôi code điiii

public class Program {
    private Scanner sc;

    public Program() {
        this.sc = new Scanner(System.in);
    }

    public int Menu() {
        System.out.println("\n*** BKAP - Design Pattern ***\n");

        System.out.println("1. Category Manager");
        System.out.println("2. Product Manager");
        System.out.println("3. Exit.");

        System.out.println("++++ Choose a option 1 to 3 ++++");

        int option = sc.nextInt();

        while (option < 1 || option > 3) {
            System.out.println("You need choose 1 to 3 !");

            option = sc.nextInt();
        }

        return option;
    }

    public void Run() {
        CategoryManager cateManager = new CategoryManager();
        ProductManager proManager = new ProductManager();

        int option = 0;

        do {
            option = Menu();

            switch (option) {
                case 1:
                    cateManager.MenuManager();
                    break;
                case 2:
                    proManager.MenuManager();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
        while (option > 0 && option < 4);
    }

    public static void main(String[] args) {
        Program program = new Program();

        program.Run();
    }
}
