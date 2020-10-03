//This is the model class for the Budget table in the Database
package com.example.eventplanner;

public class Budgets {
    private int id;
    private  String budgetName, padiAmount, amount,note;
    private int eid;
    //private  double ;

    public Budgets(){

    }

    public Budgets(int id, String budgetName, String padiAmount, String amount, String note, int eid) {
        this.id = id;
        this.budgetName = budgetName;
        this.padiAmount = padiAmount;
        this.amount = amount;
        this.note = note;
        this.eid = eid;
    }

    public Budgets(int id, String budgetName, String padiAmount, String amount, String note){
        this.id = id;
        this.budgetName = budgetName;
        this.padiAmount = padiAmount;
        this.amount = amount;
        this.note = note;

    }
    public Budgets(String budgetName,String padiAmount, String amount, String note){
        this.budgetName = budgetName;
        this.padiAmount = padiAmount;
        this.amount = amount;
        this.note = note;

    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getBudgetName() {
        return budgetName;
    }
    public void setBudgetName(String budgetName){
        this.budgetName = budgetName;
    }
    public String getPadiAmount() {
        return padiAmount;
    }
    public void setPadiAmount(String padiAmount){
        this.padiAmount = padiAmount;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
