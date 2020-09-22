package com.example.eventplanner;

public class ShoppingLists {
    private int id;
    private String shoppingName, qty, price, note;
    public  ShoppingLists(){

    }
    public ShoppingLists(int id,String shoppingName, String qty,String price ,String note){
        this.id=id;
        this.shoppingName=shoppingName;
        this.qty=qty;
        this.price=price;
        this.note=note;
    }
    public ShoppingLists(String shoppingName, String qty,String price ,String note){
        this.shoppingName=shoppingName;
        this.qty=qty;
        this.price=price;
        this.note=note;
    }
    public int getId(){return  id;}
    public void setId(int id){
        this.id = id;
    }
    public String getShoppingName() {
        return shoppingName;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setNote(String note){
        this.note = note;
    }

}
