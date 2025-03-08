package com.devturtle.service;

import com.devturtle.model.Expense;
import com.devturtle.repository.ExpenseRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

// converts JSON-Object, format
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public int add(String description, int amount) {
        Expense expense = new Expense();
        expense.updateDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        expense.updateDescription(description);
        expense.updateAmount(amount);
        return expenseRepository.add(expense);
    }

    public List<Expense> list() {
        return expenseRepository.findAll();
    }

    public int update(int id, String description, Integer amount) {
        Expense expense = expenseRepository.findById(id);
        // null check
        if (description != null) {
            expense.updateDescription(description);
        }
        if (amount != null) {
            expense.updateAmount(amount);
        }
        return expenseRepository.update(id, expense);
    }

    public void delete(int id) {
        expenseRepository.delete(id);
    }

    public int summary() {
        return expenseRepository.sum(expenseRepository.findAll());
    }

    public int summaryByMonth(int month) {
        return expenseRepository.sum(expenseRepository.findByMonth(month));
    }
}
