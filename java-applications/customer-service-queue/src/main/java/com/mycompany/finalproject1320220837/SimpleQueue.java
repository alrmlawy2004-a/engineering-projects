/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject1320220837;

/**
 *
 * @author H.P
 */
class SimpleQueue {
    Customer[] queue;
    int front = 0;
    int rear = 0;

    public SimpleQueue(int size) {
        queue = new Customer[size];
    }

    public void enqueue(Customer c) {
        queue[rear++] = c;
    }

    public Customer dequeue() {
        return queue[front++];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("No customers in queue");
            return;
        }
        System.out.println("Customers in queue:");
        for (int i = front; i < rear; i++) {
            System.out.println(queue[i].waitingId);
        }
    }
}
