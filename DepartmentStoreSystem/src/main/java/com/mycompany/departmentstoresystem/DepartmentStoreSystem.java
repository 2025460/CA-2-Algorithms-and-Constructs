/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.departmentstoresystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author emcav
 */
public class DepartmentStoreSystem {

    private static Scanner sc = new Scanner(System.in);
    private static List<String> applicants = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();

    // ======================================================
    //                     MAIN MENU
    // ======================================================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   DEPARTMENT STORE EMPLOYEE SYSTEM    ");
        System.out.println("=======================================");

        boolean running = true;

        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Sort Employee List");
            System.out.println("4. Generate Employee Hierarchy");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit System");
            System.out.println("7. Load & Sort Applicants File");   // ← NUEVA OPCIÓN
            System.out.print("Select an option: ");

            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    addEmployee();
                    break;

                case "2":
                    searchEmployee();
                    break;

                case "3":
                    sortEmployees();
                    break;

                case "4":
                    generateHierarchy();
                    break;

                case "5":
                    displayEmployees();
                    break;

                case "6":
                    System.out.println("Exiting system!");
                    running = false;
                    break;

                case "7":   // ← NUEVO CASE
                    loadApplicantsFromFile();
                    sortApplicants();
                    displayFirst20Applicants();
                    break;

                default:
                    System.out.println("Invalid option. Please select 1–7.");
            }
        }
    }

    // ======================================================
    //                  ADD EMPLOYEE
    // ======================================================
    private static void addEmployee() {

        System.out.println("\n--- ADD NEW EMPLOYEE ---");

        // NAME
        System.out.print("Enter employee name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }
        if (!name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            System.out.println("Error: Name must contain only letters. Numbers or symbols are not allowed.");
            return;
        }

        // Normalize name
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        // Check duplicates
        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                System.out.println("Error: An employee with this name already exists.");
                return;
            }
        }

        // MANAGER TYPE
        System.out.println("Select Manager Type:");
        System.out.println("1. Floor Manager");
        System.out.println("2. Assistant Manager");
        System.out.println("3. General Manager");
        System.out.print("Choose option: ");

        String managerChoice = sc.nextLine().trim();
        String managerType = "";

        switch (managerChoice) {
            case "1":
                managerType = "Floor Manager";
                break;
            case "2":
                managerType = "Assistant Manager";
                break;
            case "3":
                managerType = "General Manager";
                break;
            default:
                System.out.println("Error: Invalid Manager Type.");
                return;
        }

        // DEPARTMENT
        System.out.println("Select Department:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Home");
        System.out.print("Choose option: ");

        String deptChoice = sc.nextLine().trim();
        Department department;

        switch (deptChoice) {
            case "1":
                department = new Department("Electronics");
                break;
            case "2":
                department = new Department("Clothing");
                break;
            case "3":
                department = new Department("Home");
                break;
            default:
                System.out.println("Error: Invalid Department.");
                return;
        }

        // CREATE EMPLOYEE
        Employee emp = new Employee(name, managerType, department);
        employees.add(emp);

        System.out.println("\nEmployee added successfully!");
        System.out.println("Name: " + name);
        System.out.println("Manager Type: " + managerType);
        System.out.println("Department: " + department.getName());
    }

    // ======================================================
    //                DISPLAY EMPLOYEES
    // ======================================================
    private static void displayEmployees() {

        System.out.println("\n--- EMPLOYEE LIST ---");

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("Total Employees: " + employees.size());
        System.out.println("---------------------------------------");
        System.out.printf("%-20s %-20s %-20s\n", "Name", "Manager Type", "Department");
        System.out.println("---------------------------------------");

        for (Employee e : employees) {
            System.out.printf("%-20s %-20s %-20s\n",
                    e.getName(),
                    e.getManagerType(),
                    e.getDepartment().getName());
        }

        System.out.println("---------------------------------------");
    }

    // ======================================================
    //                SORT EMPLOYEES (RECURSIVE)
    // ======================================================
    private static void sortEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employees to sort.");
            return;
        }

        System.out.println("\nSorting employees alphabetically (recursive)...");

        Sorter.recursiveSort(employees);

        System.out.println("Sorting completed!");

        // Display first 20 employees
        System.out.println("\n--- FIRST 20 SORTED EMPLOYEES ---");

        int limit = Math.min(20, employees.size());

        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i);
            System.out.printf("%-20s %-20s %-20s\n",
                    e.getName(),
                    e.getManagerType(),
                    e.getDepartment().getName());
        }

        System.out.println("---------------------------------------");
    }

    // ======================================================
    //                SEARCH EMPLOYEE (RECURSIVE)
    // ======================================================
    private static void searchEmployee() {

        if (employees.isEmpty()) {
            System.out.println("\nNo employees available to search.");
            return;
        }

        System.out.print("\nEnter employee name to search: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }

        // Normalize input
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        System.out.println("\nSearching recursively for: " + formattedName + "...");

        Employee result = Searcher.recursiveSearch(employees, formattedName);

        if (result == null) {
            System.out.println("\nNo employee found with the name: " + formattedName);
        } else {
            System.out.println("\nEmployee found:");
            System.out.println("---------------------------------------");
            System.out.printf("%-20s %-20s %-20s\n",
                    result.getName(),
                    result.getManagerType(),
                    result.getDepartment().getName());
            System.out.println("---------------------------------------");
        }
    }

    // ======================================================
    //                GENERATE HIERARCHY (TREE)
    // ======================================================
    private static void generateHierarchy() {

        if (employees.isEmpty()) {
            System.out.println("No employees available to generate hierarchy.");
            return;
        }

        BinaryTree tree = new BinaryTree();

        for (Employee e : employees) {
            tree.insert(e);
        }

        tree.displayHierarchy();
    }

    // ======================================================
    //          LOAD APPLICANTS FROM FILE
    // ======================================================
    private static void loadApplicantsFromFile() {
        applicants.clear();

        try (Scanner fileScanner = new Scanner(new java.io.File("Applicants_Form.txt"))) {

            while (fileScanner.hasNextLine()) {
                String name = fileScanner.nextLine().trim();

                if (!name.isEmpty()) {
                    applicants.add(name);
                }
            }

            System.out.println("\nApplicants file loaded successfully!");
            System.out.println("Total names found: " + applicants.size());

        } catch (Exception e) {
    System.out.println("Error: Could not read Applicants_Form.txt");
    System.out.println("Details: " + e.getMessage());
     }
    }

    // ======================================================
    //          SORT APPLICANTS (RECURSIVE)
    // ======================================================
    private static void sortApplicants() {

        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded. Load the file first.");
            return;
        }

        System.out.println("\nSorting applicants (recursive)...");

        Sorter.recursiveSortStrings(applicants);

        System.out.println("Sorting completed!");
    }

    // ======================================================
    //      DISPLAY FIRST 20 APPLICANTS
    // ======================================================
    private static void displayFirst20Applicants() {

        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        System.out.println("\n--- FIRST 20 APPLICANTS ---");

        int limit = Math.min(20, applicants.size());

        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + applicants.get(i));
        }
    }
}