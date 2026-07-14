CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
); 

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));


COMMIT;

SET SERVEROUTPUT ON;

--Control structures

DECLARE
    CURSOR cust_cur IS
        SELECT CustomerID, 
            TRUNC(MONTHS_BETWEEN(SYSDATE, DOB)/12) AS Age 
        FROM Customers;

BEGIN
    FOR cust_rec IN cust_cur LOOP
        IF cust_rec.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Discount applied successfully for Customer ID: '
                || cust_rec.CustomerID
            );
        END IF;
    END LOOP;

    COMMIT;
END;
/

SELECT * FROM Loans;

ALTER TABLE Customers
ADD IsVIP VARCHAR2(5); 

DECLARE 
    CURSOR vip_cur IS
        SELECT CustomerID, Balance FROM Customers;

BEGIN 
    FOR cust_rec IN vip_cur LOOP
    IF cust_rec.Balance > 10000 THEN

        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = cust_rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE('Customer ' || cust_rec.CustomerID || ' promoted to VIP');

    END IF;
    END LOOP;

    COMMIT;
END;
/

SET SERVEROUTPUT ON;
DECLARE
CURSOR loan_cur IS 
    SELECT loanID, CustomerID, EndDate FROM LOANS
        where Enddate between Sysdate and Sysdate + 30;

BEGIN 
    FOR loan_rec IN loan_cur LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Customer' || loan_rec.CustomerID ||
    ' has loan ID ' || loan_rec.loanID || ' due on ' || TO_CHAR(loan_rec.EndDate, 'DD-MM-YYYY'));

    END LOOP;
END;
/   


--Error handling

--scenario 1

create or replace procedure safetransferfunds(
    p_from_account in number,
    p_to_account in number,
    p_amount in number
) is v_balance number;

begin
    --sender balance
    select balance into v_balance from accounts where accountID = p_from_account;   
    --check insufficient funds
    if v_balance < p_amount then raise_application_error(
        'Insufficient funds'
    );
    end if;

--deduct from sender
    update accounts
    set balance = balance - p_amount where accountID = p_from_account;

--add to receiver
    update accounts
     set balance = balance + p_amount where accountID = p_to_account;

    commit;

    dbms_ouput.put_line("Transfer successful");

exception
    when others then rollback;

    dbms_ouput.put_line("error: " || SQLERRM);

END;
/

--Scenario 2
create or replace procedure UpdateSalary(
    p_empid in number,
    p_increase_percentage in number
) is v_salary number;

begin
    --fetch old salary
    select salary into v_salary from employees where employeeid = p_empid;

    --salary increase
    update employees
    set salary = salary + (salary*p_increase_percentage / 100) where employeeID = p_empid;
    commit;

    dbms.output.put_line("Salary updated");

exception
    when no_data_found then dbms.output.put_line("Error: EmployeeID does not exist");
    
    when others then dbms.output.put_line("Error: " || SQLERRM);

END;
/

--Scenario 3
create or replace procedure AddNewCustomer (
    p_customerID in number,
    p_name in varchar2,
    p_dob in date,
    p_balance in number
    
) IS 

BEGIN
    insert into Customers(customerid, name, dob, balance, lastmodified) 
    values(p_customerId, p_name, p_dob, p_balance, sysdate);

    commit;

    dbms_output.put_line("customer added successfully");

exception
    when dup_val_on_index then 
    dbms_output.put_line('Error: Customer ID already exists');

    when others then dbms_output.put_line('Error' || SQLERRM);
end;
/

--Stored Procedures
--Scenario 1

create or replace procedure ProcessMonthlyInterest is 
begin 
    update accounts
    set balance = balance * 1.01 where accounttype = savings;

    commit;

    dbms_output.put_line("Monthly interest processes successfully");

exception
    when othere then rollback;
    dbms_ouput.put_line("Error" || SQLERRM);

end;
/


--Scenario 2

create or replace procedure UpdateEmployeeBonus(
    p_bonus in number,
    p_dept in varchar2
) is 

BEGIN
    update employees
    set salary = salary + (salary * p_bonus / 100) where department = p_dept;
    commit;

    dbms_output.put_line('salary updated');

exception
    when others then rollback;
    dbms_output.put_line('Error' || SQLERRM);

end;
/


--Scenario 3

create or replace procedure TransferFunds(
    p_account_from in number,
    p_account_to in number,
    p_amount in number
) is v_balance number;

BEGIN
    select balance into v_balance from accounts where accountid = p_account_from;
    if v_balance < p_amount then raise_application_error(-20001, 'Insufficient Funds');
    end if;

    update accounts
    set balance = balance - p_amount where accountID = p_account_from;

    update accounts
    set balance = balance + p_amount where accountID = p_account_to;

    commit;

    dbms_output.put_line('Account updated successfully');

exception
    when others then rollback;
    dbms_output.put_line('Error' || SQLERRM);
end;
/



