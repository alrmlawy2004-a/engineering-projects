package queue;

class SimpleQueue {
    private final Customer[] queue;
    private int front;
    private int rear;
    private int count;

    public SimpleQueue(int size) {
        if (size <= 0) throw new IllegalArgumentException("Capacity must be positive.");
        queue = new Customer[size];
    }

    public void enqueue(Customer customer) {
        if (isFull()) throw new IllegalStateException("The waiting queue is full.");
        queue[rear] = customer;
        rear = (rear + 1) % queue.length;
        count++;
    }

    public Customer dequeue() {
        if (isEmpty()) throw new IllegalStateException("The waiting queue is empty.");
        Customer customer = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;
        return customer;
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull() { return count == queue.length; }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("No customers in queue");
            return;
        }
        System.out.println("Customers in queue:");
        for (int i = 0; i < count; i++) {
            System.out.println(queue[(front + i) % queue.length].waitingId);
        }
    }
}
