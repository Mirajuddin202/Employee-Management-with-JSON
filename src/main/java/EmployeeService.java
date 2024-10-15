import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeService {

    private static final String FILE_PATH = "./src/main/resources/employees.json";

    public void addEmployee(Scanner scanner) throws IOException, ParseException {
        System.out.println("Enter Employee Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Designation:");
        String designation = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phone = scanner.nextLine();

        Employee employee = new Employee(name, designation, phone);

        // Read existing data
        JSONArray employeeArray = readJSONFile();

        // Add new employee to the array
        employeeArray.add(employee.toJSON());

        // Write updated array back to the file
        writeJSONFile(employeeArray);
        System.out.println("Employee added successfully!");
    }

    public void viewEmployees() throws IOException, ParseException {
        JSONArray employeeArray = readJSONFile();

        if (employeeArray.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Object obj : employeeArray) {
                JSONObject employee = (JSONObject) obj;
                System.out.println("Name: " + employee.get("name"));
                System.out.println("Designation: " + employee.get("designation"));
                System.out.println("Phone: " + employee.get("phone"));
                System.out.println();
            }
        }
    }

    public void updateEmployee(Scanner scanner) throws IOException, ParseException {
        System.out.println("Enter the name of the employee to update:");
        String name = scanner.nextLine();

        JSONArray employeeArray = readJSONFile();
        boolean found = false;

        for (Object obj : employeeArray) {
            JSONObject employee = (JSONObject) obj;

            if (employee.get("name").equals(name)) {
                System.out.println("Enter new Designation:");
                String newDesignation = scanner.nextLine();
                System.out.println("Enter new Phone Number:");
                String newPhone = scanner.nextLine();

                employee.put("designation", newDesignation);
                employee.put("phone", newPhone);

                writeJSONFile(employeeArray);
                System.out.println("Employee updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    public void deleteEmployee(Scanner scanner) throws IOException, ParseException {
        System.out.println("Enter the name of the employee to delete:");
        String name = scanner.nextLine();

        JSONArray employeeArray = readJSONFile();
        boolean found = false;

        for (int i = 0; i < employeeArray.size(); i++) {
            JSONObject employee = (JSONObject) employeeArray.get(i);

            if (employee.get("name").equals(name)) {
                employeeArray.remove(i);
                writeJSONFile(employeeArray);
                System.out.println("Employee deleted successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    private JSONArray readJSONFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return (JSONArray) parser.parse(reader);
        } catch (FileNotFoundException e) {
            return new JSONArray();  // Return empty JSON array if file not found
        }
    }

    private void writeJSONFile(JSONArray employeeArray) throws IOException {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(employeeArray.toJSONString());
            file.flush();
        }
    }
}
