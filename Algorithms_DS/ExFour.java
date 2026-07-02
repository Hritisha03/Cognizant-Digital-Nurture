class Employee {

    int employeeId;
    String name;
    String position;
    double salary;

    Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    void display() {
        System.out.println(employeeId + " | " + name + " | " + position + " | ₹" + salary);
    }
}

public class ExFour {

    static Employee[] employees = new Employee[10];
    static int count = 0;

    // Add Employee
    static void addEmployee(Employee e) {
        if (count < employees.length) {
            employees[count++] = e;
            System.out.println("Employee Added.");
        } else {
            System.out.println("Array is Full.");
        }
    }

    // Search Employee
    static void searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("\nEmployee Found:");
                employees[i].display();
                return;
            }
        }
        System.out.println("Employee Not Found.");
    }

    // Traverse Employees
    static void traverseEmployees() {
        System.out.println("\nEmployee Records:");
        for (int i = 0; i < count; i++) {
            employees[i].display();
        }
    }

    // Delete Employee
    static void deleteEmployee(int id) {

        for (int i = 0; i < count; i++) {

            if (employees[i].employeeId == id) {

                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }

                employees[count - 1] = null;
                count--;

                System.out.println("Employee Deleted.");
                return;
            }
        }

        System.out.println("Employee Not Found.");
    }

    public static void main(String[] args) {

        addEmployee(new Employee(101, "Amit", "Manager", 60000));
        addEmployee(new Employee(102, "Riya", "Developer", 50000));
        addEmployee(new Employee(103, "John", "Tester", 45000));

        traverseEmployees();

        searchEmployee(102);

        deleteEmployee(101);

        traverseEmployees();
    }
}