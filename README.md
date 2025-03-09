# expense-tracker
A simple and lightweight CLI application to track your daily expenses effortlessly.  
This project is based on the guide from [roadmap.sh](https://roadmap.sh/projects/expense-tracker).

## Table of contents
[Features](#features)  
[Installation](#installation)  
[Usage](#usage)  
[Example](#example) 

## Features
- Add an expense with a description and amount.
- Update or delete an existing expense.
- View all recorded expenses.
- Get a summary of your total expenses.
- View expenses for a specific month (current year).

## Installation
Type the following commands on your terminal such as Command Prompt, Git Bash etc
### 1. Clone the repository
```sh
git clone https://github.com/peter-jaesungyu/expense-tracker
cd expense-tracker
```
### 2. Build and Install
**Mac/Linux**  
Make executable file
```sh
./gradlew installDist
```
Add PATH
```sh
export PATH=$PATH:$(pwd)/build/install/expense-tracker/bin
```
**Windows**  
Make executable file
```sh
gradlew installDist
```
Add PATH
```sh
set PATH=%PATH%;%CD%\build\install\expense-tracker\bin
```
> **🔎 Note:**
> The PATH modification is temporary. To make it permanent, update your system’s environment variables.

## Usage
Once installed, you can use the following commands:
```text
expense-tracker add --description "Lunch" --amount 20    # Add an expense  
expense-tracker update --id 1 --description "Dinner" --amount 25  # Update an expense  
expense-tracker delete --id 1   # Delete an expense  
expense-tracker list    # Show all expenses  
expense-tracker summary    # Show total expenses  
expense-tracker summary --month 8   # Show expenses for August  
```

## Example
```text
$ expense-tracker add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)

$ expense-tracker add --description "Dinner" --amount 10
# Expense added successfully (ID: 2)

$ expense-tracker list
# ID  Date       Description  Amount
# 1   2024-08-06  Lunch        $20
# 2   2024-08-06  Dinner       $10

$ expense-tracker summary
# Total expenses: $30

$ expense-tracker delete --id 2
# Expense deleted successfully

$ expense-tracker summary
# Total expenses: $20

$ expense-tracker summary --month 8
# Total expenses for August: $20
```