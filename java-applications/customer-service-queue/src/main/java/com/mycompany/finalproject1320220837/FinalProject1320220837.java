/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalproject1320220837;


import java.util.Scanner;

/**
 *
 * @author H.P
 */
   public class FinalProject1320220837 {

    static SimpleQueue waitingQueue = new SimpleQueue(20);
    static SimpleStack servedStack = new SimpleStack(20);

    static User[] users = new User[5];
    static int userCount = 0;

    static int waitingCounter = 1;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Users
        users[userCount++] = new User("Anas", 123, false);
        users[userCount++] = new User("Alaa", 123, true);

        while (true) {

            System.out.println("\nWelcome to CompanyName");
            System.out.println("Press Enter to get your waiting ID");
            System.out.println("Or enter username:");

            String input = in.nextLine();

            // Customer
            if (input.isEmpty()) {
                Customer c = new Customer(waitingCounter++);
                waitingQueue.enqueue(c);
                System.out.println("Your waiting ID is: " + c.waitingId);
            }
            // Login
            else {
                User user = login(input, in);

                if (user == null) {
                    System.out.println("Wrong username or password");
                } else if (user.isAdmin) {
                    adminMenu(in);
                } else {
                    employeeMenu(in);
                }
            }
        }
    }

    // Login 
    static User login(String username, Scanner in) {
        System.out.print("Password: ");
        int pass = Integer.parseInt(in.nextLine());

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)
                    && users[i].password == pass
                    && users[i].isActive) {
                return users[i];
            }
        }
        return null;
    }

    // Employee Menu ===================== 
    static void employeeMenu(Scanner in) {

        while (true) {
            System.out.println("\nEmployee Menu");
            System.out.println("1- Serve next customer");
            System.out.println("2- Check last served customer");
            System.out.println("3- View all served customers");
            System.out.println("0- Logout");

            int choice = Integer.parseInt(in.nextLine());

            if (choice == 1) {
                serveCustomer(in);
            } else if (choice == 2) {
                if (!servedStack.isEmpty())
                    printCustomer(servedStack.peek());
                else
                    System.out.println("No customers served yet");
            } else if (choice == 3) {
                servedStack.printAll();
            } else if (choice == 0) {
                break;
            }
        }
    }

    /* ===================== Serve Customer ===================== */
    static void serveCustomer(Scanner in) {

        if (waitingQueue.isEmpty()) {
            System.out.println("No customers in queue");
            return;
        }

        Customer c = waitingQueue.dequeue();

        System.out.println("Serving customer " + c.waitingId);

        System.out.print("Name: ");
        c.name = in.nextLine();

        System.out.print("ID Number: ");
        c.idNumber = in.nextLine();

        System.out.print("Address: ");
        c.address = in.nextLine();

        System.out.print("Issue: ");
        c.issue = in.nextLine();

        System.out.print("Solved? (1 yes / 0 no): ");
        c.solved = in.nextLine().equals("1");

        servedStack.push(c);
        System.out.println("Customer served successfully");
    }

    /* ===================== Admin Menu ===================== */
    static void adminMenu(Scanner in) {

        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1- View customers queue");
            System.out.println("2- View last served customer");
            System.out.println("0- Logout");

            int choice = Integer.parseInt(in.nextLine());

            if (choice == 1) {
                waitingQueue.printQueue();
            } else if (choice == 2) {
                if (!servedStack.isEmpty())
                    printCustomer(servedStack.peek());
                else
                    System.out.println("No customers served yet");
            } else if (choice == 0) {
                break;
            }
        }
    }

    // Print Customer 
    static void printCustomer(Customer c) {
        System.out.println("Name: " + c.name);
        System.out.println("ID: " + c.idNumber);
        System.out.println("Address: " + c.address);
        System.out.println("Issue: " + c.issue);
        System.out.println("Solved: " + (c.solved ? "Yes" : "No"));
    }
}