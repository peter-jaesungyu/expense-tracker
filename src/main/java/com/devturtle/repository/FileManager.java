package com.devturtle.repository;

import com.devturtle.model.Expense;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileManager {
    private File jsonFile;

    public FileManager() {
        this.jsonFile = new File(System.getProperty("user.dir") + "\\expense-tracker.json");
        ensureFileExists();
    }

    public void changeFilePath(String newFilePath) {
        this.jsonFile = new File(newFilePath + "expense-tracker.json");
        ensureFileExists();
    }

    public Map<Integer, Expense> load() {
        Gson gson = new Gson();
        Map<Integer, Expense> expenseMap = new LinkedHashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile))) {
            Type type = new TypeToken<Map<Integer, Expense>>() {}.getType();
            Map<Integer, Expense> loadedMap = gson.fromJson(bufferedReader, type);

            if (loadedMap != null) {
                expenseMap = loadedMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in load() of FileManager! Reason: " + e.getMessage());
        }

        return expenseMap;
    }

    public void save(Map<Integer, Expense> expenseMap) {
        ensureFileExists();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonFile))) {
            Gson gson = new Gson();
            gson.toJson(expenseMap, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in save() of FileManager!");
        }
    }

    private void ensureFileExists() {
        try {
            File parentDir = jsonFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // create parent directories
            }
            if (!jsonFile.exists()) {
                jsonFile.createNewFile(); // create new file if not exists
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating file: " + jsonFile.getAbsolutePath());
        }
    }
}
