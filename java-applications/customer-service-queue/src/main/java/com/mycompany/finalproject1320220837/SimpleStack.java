/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject1320220837;

/**
 *
 * @author H.P
 */
class SimpleStack {
    Customer[] stack;
    int top = 0;

    public SimpleStack(int size) {
        stack = new Customer[size];
    }

    public void push(Customer c) {
        stack[top++] = c;
    }

    public Customer peek() {
        return stack[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("No served customers");
            return;
        }
        for (int i = 0; i < top; i++) {
            Customer c = stack[i];
            System.out.println(
                (i + 1) + "- " + c.name + " | " +
                c.idNumber + " | " +
                (c.solved ? "Solved" : "Not Solved")
            );
        }
    }
}
