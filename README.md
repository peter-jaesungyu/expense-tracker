# expense-tracker
A simple CLI app for tracking your expenses  
This project is based on the project guide from https://roadmap.sh/projects/expense-tracker

## What can I do for you?
- Users can add an expense with a description and amount.
- Users can update an expense.
- Users can delete an expense.
- Users can view all expenses.
- Users can view a summary of all expenses.
- Users can view a summary of expenses for a specific month (of current year).  

## How to install
Type the following commands on your terminal (CMD, Git Bash etc)
```text
# Download this program
git clone https://github.com/peter-jaesungyu/expense-tracker

# Move to the directory where this program's installed
cd expense-tracker

# Make executable file
./gradlew installDist # Mac/Linux
gradlew installDist # Windows

# Add PATH
export PATH=$PATH:$(pwd)/build/install/expense-tracker/bin
```
## Example
```test
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
