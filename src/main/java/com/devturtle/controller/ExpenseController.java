package com.devturtle.controller;

import com.devturtle.model.Expense;
import com.devturtle.service.ExpenseService;

import java.util.List;

public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public int add(String description, int amount) {
        return expenseService.add(description, amount);
    }

    public List<Expense> list() {
        return expenseService.list();
    }

    public int update(int id, String description, Integer amount) {
        return expenseService.update(id, description, amount);
    }

    public void delete(int id) {
        expenseService.delete(id);
    }

    public int summary() {
        return expenseService.summary();
    }

    public int summaryByMonth(int month) {
        return expenseService.summaryByMonth(month);
    }
}
