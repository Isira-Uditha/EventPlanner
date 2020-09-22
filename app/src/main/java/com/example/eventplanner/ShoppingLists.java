package com.example.eventplanner;

public class ShoppingLists {
    private int ids;
    private String shoppingName, qty, price, note;
    public  ShoppingLists(){

    }
    public ShoppingLists(int ids,String shoppingName, String qty,String price ,String note){
        this.ids=ids;
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
    public int getId(){return  ids;}
    public void setId(int ids){
        this.ids = ids;
    }
    public String getShoppingName() {
        return shoppingName;
    }
    public void setShoppingName(String shoppingName){this.shoppingName=shoppingName;}
    public String getQty() {
        return qty;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

}
