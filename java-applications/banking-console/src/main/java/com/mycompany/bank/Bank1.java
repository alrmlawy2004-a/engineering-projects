/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank;

/**
 *
 * @author H.P
 */
public class Bank1 {
    private int accountno;
    private String name;
    private float amount;
    
    public void setInsert(int a ,String n ,float m){
        this.accountno = a;
        this.amount = m;
        this.name = n;
    }
    public void setdeposit(float m){
        this.amount = m +  this.amount;
        System.out.println(m);
    }
       public void setWithdraw(float m){
           if(m > amount){
               System.out.println("هناك خطا ");
           }else{
       
        this.amount =this.amount-m;
           }
    }
       public void chevkBalance(){
           System.out.println("amount" + this.amount);
       }

    @Override
    public String toString() {
        return "Bank1{" + "accountno=" + accountno + ", name=" + name + ", amount=" + amount + '}';
    }
       
       
}
