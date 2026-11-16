
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method to read employee data from file
    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return reader.readLine().split(",");
        }
    }

    // Method to write employee data to file
    private static void writeEmployees(String[] employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        }
    }

    // Method to search for an employee
    private static boolean searchEmployee(String employeeName) throws IOException {
        for (String employee : readEmployees()) {
            if (employee.equals(employeeName)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager <argument>");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readEmployees()) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                System.out.println(String.join(",", employees));
                Random random = new Random();
                System.out.println(employees[random.nextInt(employees.length)]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEE_FILE, true));
                writer.write(", " + args[0].substring(1));
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                if (searchEmployee(args[0].substring(1))) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                boolean inWord = false;
                int wordCount = 0;

                for (char character : String.join(",", employees).toCharArray()) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found "
                        + String.join(",", employees).length());
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployees(employees);
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();

                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(args[0].substring(1));

                writeEmployees(employeeList.toArray(new String[0]));
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }
}
