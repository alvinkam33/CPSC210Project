# Banking Management System 

## Create and Manage Accounts

This application is a banking system that allows the user to create a bank account
and manage it. A key feature is the inclusion of some of the unique functionality of different types
of accounts, such as *chequeing, savings, and RESP* accounts. Otherwise, users will still be able to
perform general operations on these accounts, such as *deposits, withdrawls, and transfers*. Therefore,
this application is to be used by anyone who would like to setup and manage a bank account.

I am interested in doing this project as a way of integrating the worlds of computer science
and business together. As a student enrolled in the BUCS program, one of my main objectives is
to observe how technology can be utilized in business-related fields. In particular, the internship 
that I attended at a bank when I was in high school really caught my attention when I saw how 
technology has transformed banking operations behind the scenes. Overall, this project will be a first step
in trying to create that connection on my own.

## How to Run

Run the main.java file. Simple!

## User Stories

- As a user, I want to be able to create a personal bank account
- As a user, I want to be able to add an account type of my choice to my bank account
- As a user, I want to be able to add deposit/withdraw funds to/from an account type that I choose
- As a user, I want to be able to transfer funds from one of my accounts to another
- As a user, I want to be able to access the information of all of my accounts at once
- As a user, I want to be able to keep my accounts secure by setting a PIN number

- As a user, I want to be able to have the option to save my bank account data in the list of operations
- As a user, when I rerun the application, I want the option to load the bank account that I saved

## Instructions for Grader

- You can generate the first required event by selecting "New Account" on the first panel that appears
- You can generate the second required event by selecting "Open an Account" in the banking operations panel, and further setting a PIN to the account type
- You can view all of the data on the bank account as well as account types currently opened by selecting "Get Info" in the banking operations panel
- You can trigger my audio component by essentially attempting to complete any processes. For successful processes (e.g. making a new account, deposit/withdrawls), a cash register sound effect should play. For unsuccessful processes (e.g. invalid input, negative balance, wrong PIN), an error sound should play
- You can save the state (bank account data) of the application by clicking the "Save Account Data" button in the list of banking operations
- You can load the state of the application by clicking the "Load Account" button on the first panel that appears (if no data is found, it will cause you to make a new account instead) 

## Phase 4: Task 2

Option Chosen:
- Test and design a class that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.
BankAccount, Chequing, RegisteredEducationSavingsPlan, and Savings are all robust classes that have methods that throw checked exceptions.

## Phase 4: Task 3

Issues Identified:
- Each account type (Chequing, Savings, RegisteredEducationSavingsPlan) has two responsibilities (depositing/withdrawing and displaying info) that can be split into another class
- BankAccountInterface is a very long class that is difficult to read, and has a lot more than one task

Fixes:
- Created new class InfoDisplayer that is solely responsible for displaying information of each account type
- Improved readability and cohesion of BankAccountInterface by splitting tasks into separate classes (Panels and Sounds)

