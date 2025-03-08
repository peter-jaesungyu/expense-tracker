package com.devturtle.model;

public class Expense {
    private int id;
    private String date;
    private String description;
    private int amount;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public void updateId(int id) {
        this.id = id;
    }

    public void updateDate(String date) {
        this.date = date;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("# %-4d %-12s %-18s $%-6d", id, date, description, amount);
    }
}
