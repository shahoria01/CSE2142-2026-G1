
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read employee names from the employee file
    private static String[] loadEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return reader.readLine().split(",");
        }
    }

    // Save the updated employee list to the employee file
    private static void saveEmployeesToFile(String[] employeeNames) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employeeNames));
        }
    }

    // Check whether the given employee name exists in the file
    private static boolean isEmployeeFound(String employeeName) throws IOException {
        for (String currentEmployee : loadEmployeesFromFile()) {
            if (currentEmployee.equals(employeeName)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // Validate the number of command-line arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager <argument>");
            return;
        }

        // Validate the command entered by the user
        if (!args[0].equals("l") && !args[0].equals("s")
                && !args[0].equals("c")
                && !args[0].startsWith("+")
                && !args[0].startsWith("?")
                && !args[0].startsWith("u")
                && !args[0].startsWith("d")) {
            System.out.println("Invalid argument!");
            System.out.println("Valid arguments: l, s, +name, ?name, c, uname, dname");
            return;
        }

        // Ensure an employee name is provided when required
        if ((args[0].startsWith("+") || args[0].startsWith("?")
                || args[0].startsWith("u") || args[0].startsWith("d"))
                && args[0].length() == 1) {
            System.out.println("Employee name is required!");
            return;
        }

        // Display all employee names
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employeeName : loadEmployeesFromFile()) {
                    System.out.println(employeeName);
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        // Display all employees and select one randomly
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employeeNames = loadEmployeesFromFile();
                System.out.println(String.join(",", employeeNames));

                Random randomGenerator = new Random();
                System.out.println(employeeNames[
                        randomGenerator.nextInt(employeeNames.length)]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        // Add a new employee to the file
        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                    writer.write(", " + args[0].substring(1));
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        // Search for an employee by name
        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                if (isEmployeeFound(args[0].substring(1))) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        // Count the total number of words in employee names
        } else if (args[0].equals("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employeeNames = loadEmployeesFromFile();

                int wordCount = Arrays.stream(employeeNames)
                        .mapToInt(employeeName -> employeeName.trim().isEmpty()
                                ? 0 : employeeName.trim().split("\\s+").length)
                        .sum();

                System.out.println(wordCount + " word(s) found "
                        + String.join(",", employeeNames).length());
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");

        // Update the specified employee name
        } else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employeeNames = loadEmployeesFromFile();

                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(args[0].substring(1))) {
                        employeeNames[index] = "Updated";
                    }
                }

                saveEmployeesToFile(employeeNames);
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");

        // Delete the specified employee from the file
        } else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employeeNames = loadEmployeesFromFile();

                List<String> employeeList =
                        new ArrayList<>(Arrays.asList(employeeNames));
                employeeList.remove(args[0].substring(1));

                saveEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }
}
