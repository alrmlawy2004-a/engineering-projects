
package queue;


class Customer {
    int waitingId;
    String name;
    String idNumber;
    String address;
    String issue;
    boolean solved;

    public Customer(int waitingId) {
        this.waitingId = waitingId;
    }
}
