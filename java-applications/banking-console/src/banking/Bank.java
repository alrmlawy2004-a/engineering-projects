/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package banking;

/**
 *
 * @author H.P
 */
public class Bank {

    public static void main(String[] args) {
        Bank1 a1 = new Bank1();
        Bank1 a2 = new Bank1();
        Bank1 a3 = new Bank1();
        
        a1.setInsert(4451238, "alaa", 1000);
        a1.setdeposit(50);
        a1.setWithdraw(20);
        a1.chevkBalance();
        System.out.println(a1.toString());

        a2.setInsert(9563145, "belal", 100);
        a2.setdeposit(50);
        a2.setWithdraw(140);
        a2.chevkBalance();
        System.out.println(a2.toString());

        a3.setInsert(4451238, "ahmad", 500);
        a3.setdeposit(550);
        a3.setWithdraw(10);
        a3.chevkBalance();
        System.out.println(a3.toString());
    }
}
