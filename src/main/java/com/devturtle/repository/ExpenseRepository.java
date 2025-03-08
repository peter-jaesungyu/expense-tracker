package com.devturtle.repository;

import com.devturtle.model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ExpenseRepository {
    private final FileManager fileManager;
    private static Map<Integer, Expense> expenseMap;

    public ExpenseRepository(FileManager fileManager) {
        this.fileManager = fileManager;
        expenseMap = fileManager.load();
    }

    // add
    public int add(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Error in add(): Expense cannot be null");
        }

        int id = newId();
        expense.updateId(id);
        expenseMap.put(id, expense);
        fileManager.save(expenseMap);
        return id;
    }

    // findById
    public Expense findById(int id) {
        if (!expenseMap.containsKey(id)) {
            throw new NoSuchElementException("Error in findById() : Not found id " + id);
        }
        return expenseMap.get(id);
    }

    // update
    public int update(int id, Expense expense) {
        if (!expenseMap.containsKey(id)) {
            throw new NoSuchElementException("Error in update() : Not found id " + id);
        }

        if (expense == null) {
            throw new IllegalArgumentException("Error in update(): Expense cannot be null");
        }
        expenseMap.replace(id, expense);
        fileManager.save(expenseMap);
        return id;
    }

    // findByDate
    public List<Expense> findByMonth(int month) {
        return expenseMap.values().stream()
                .filter(obj -> LocalDate.parse(obj.getDate()).getMonthValue() == month)
                .toList();
    }

    // findAll
    public List<Expense> findAll() {
        return expenseMap.values().stream().toList();
    }

    // sum
    public int sum(List<Expense> expenses) {
        if (expenses == null) {
            throw new IllegalArgumentException("Error in sum(): Expense list cannot be null");
        }

        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(0, Integer::sum);
    }

    // delete
    public int delete(int id) {
        if (!expenseMap.containsKey(id)) {
            throw new NoSuchElementException("Error in delete() : Not found id " + id);
        }
        expenseMap.remove(id);
        fileManager.save(expenseMap);
        return id;
    }

    private int newId() {
        return expenseMap.isEmpty() ? 1 : expenseMap.keySet().stream().max(Integer::compareTo).get() + 1;
    }
}
