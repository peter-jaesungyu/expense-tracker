package com.devturtle.controller;

import com.devturtle.model.Expense;
import org.apache.commons.cli.*;

import java.time.DateTimeException;
import java.time.Month;
import java.util.List;

// Controller for resolving right methods
public class FrontController {
    private final ExpenseController expenseController;

    public FrontController(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    public void resolve(String[] args) throws ParseException, IllegalArgumentException {
        if (args == null) {
            throw new IllegalArgumentException("Error in resolve() of FrontController!\nEmpty command line arguments error!");
        }

        // Apache Commons CLI
        // create Options object
        Options options = new Options();

        // add option
        options.addOption("d", "description", true, "describes about expenses");
        options.addOption("a", "amount", true, "how much it costs");
        options.addOption("i", "id", true, "designate a specific record");
        options.addOption("m", "month", true, "calculate for total monthly expenditure");

        // create a parser
        CommandLineParser parser = new DefaultParser();

        // parse the options passed as command line arguments
        CommandLine cmd = parser.parse(options, args);

        switch (args[0].toLowerCase()) {
            case "add" :
                // hasOptions checks if option is present or not
                if (cmd.hasOption("d") & cmd.hasOption("a")) {
                    // print add
                    int id = expenseController.add(cmd.getOptionValue("d"), Integer.parseInt(cmd.getOptionValue("a")));
                    System.out.printf("Expense added successfully (ID: %d)", id);
                }
                break;
            case "list" :
                // check if invalid
                if (args.length == 1) {
                    // print list
                    List<Expense> list = expenseController.list();
                    System.out.printf("# %-4s %-12s %-18s %-6s%n", "ID", "Date", "Description", "Amount");
                    System.out.println("---------------------------------------------");
                    for (Expense item : list) {
                        System.out.println(item.toString());
                    }
                }
                break;
            case "update" :
                // check if invalid
                if (cmd.hasOption("i")) {
                    if (cmd.hasOption("d") & cmd.hasOption("a")) {
                        // print update
                        expenseController.update(Integer.parseInt(cmd.getOptionValue("i")), cmd.getOptionValue("d"), Integer.parseInt(cmd.getOptionValue("a")));
                    } else if (cmd.hasOption("d")) {
                        // print update
                        expenseController.update(Integer.parseInt(cmd.getOptionValue("i")), cmd.getOptionValue("d"), null);
                    } else if (cmd.hasOption("a")) {
                        // print update
                        expenseController.update(Integer.parseInt(cmd.getOptionValue("i")), null, Integer.parseInt(cmd.getOptionValue("a")));
                    }
                    System.out.printf("Expense updated successfully (ID: %s)", cmd.getOptionValue("i"));
                }
                break;
            case "summary" :
                // check if invalid
                if (cmd.hasOption("m")) {
                    // print monthly summary
                    int totalAmount = expenseController.summaryByMonth(Integer.parseInt(cmd.getOptionValue("m")));

                    String monthInput = cmd.getOptionValue("m");
                    String month;
                    try {
                        int monthNumber = Integer.parseInt(monthInput);
                        month = Month.of(monthNumber).name();
                    } catch (NumberFormatException | DateTimeException e) {
                        throw new IllegalArgumentException("Invalid month value: " + monthInput);
                    }
                    System.out.printf("# Total expenses for %s: $%d", month, totalAmount);
                } else if (args.length == 1) {
                    // print total summary
                    int totalAmount = expenseController.summary();
                    System.out.printf("# Total expenses: $%d", totalAmount);
                }
                break;
            case "delete" :
                // check if invalid
                if (cmd.hasOption("i")) {
                    // print delete
                    int id = Integer.parseInt(cmd.getOptionValue("id"));
                    expenseController.delete(id);
                    System.out.printf("Expense deleted successfully (ID: %d)", id);
                }
                break;
            default :
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("expense-tracker", options);
                throw new IllegalArgumentException("Error in resolve() of FrontController!\nInvalid command line arguments error!");
        }
    }
}
