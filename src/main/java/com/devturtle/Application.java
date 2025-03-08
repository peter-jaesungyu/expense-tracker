package com.devturtle;

import com.devturtle.controller.ExpenseController;
import com.devturtle.controller.FrontController;
import com.devturtle.repository.ExpenseRepository;
import com.devturtle.repository.FileManager;
import com.devturtle.service.ExpenseService;
import org.apache.commons.cli.ParseException;

public class Application {
    public static void main(String[] args) {
        try {
            FrontController frontController = new FrontController(new ExpenseController(new ExpenseService(new ExpenseRepository(new FileManager()))));
            frontController.resolve(args);
        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
