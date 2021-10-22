package com.example.homework.Model;

public class Donation {
    public int id;
    public int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String method;

    public Donation(int amount, String method){
        this.amount = amount;
        this.method = method;
}
    public Donation(){
        this.amount = 0;
        this.method = "";
}

}
