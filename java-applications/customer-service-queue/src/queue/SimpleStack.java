/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue;

/**
 *
 * @author H.P
 */
class SimpleStack {
    Customer[] stack;
    int top = 0;

    public SimpleStack(int size) {
        if (size <= 0) throw new IllegalArgumentException("Capacity must be positive.");
        stack = new Customer[size];
    }

    public void push(Customer c) {
        if (isFull()) throw new IllegalStateException("Served history is full.");
        stack[top++] = c;
    }

    public Customer peek() {
        if (isEmpty()) throw new IllegalStateException("Served history is empty.");
        return stack[top - 1];
    }

    public boolean isFull() { return top == stack.length; }

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
