
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method to read employee data from file
    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            return line.split(",");
        }
    }

    // Method to write employee data to file
    private static void writeEmployees(String[] employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        }
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
                String[] employees = readEmployees();
                for (String employee : employees) {
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
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEE_FILE, true));
                String employeeName = args[0].substring(1);
                writer.write(", " + employeeName);
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                boolean isFound = false;
                String searchName = args[0].substring(1);

                for (int i = 0; i < employees.length && !isFound; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        isFound = true;
                    }
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                String line = String.join(",", employees);
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                String employeeName = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeName)) {
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
                String employeeName = args[0].substring(1);

                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeName);

                writeEmployees(employeeList.toArray(new String[0]));
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }
}
