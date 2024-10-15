import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeManager {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        EmployeeService employeeService = new EmployeeService();

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    employeeService.addEmployee(scanner);
                    break;
                case 2:
                    employeeService.viewEmployees();
                    break;
                case 3:
                    employeeService.updateEmployee(scanner);
                    break;
                case 4:
                    employeeService.deleteEmployee(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
