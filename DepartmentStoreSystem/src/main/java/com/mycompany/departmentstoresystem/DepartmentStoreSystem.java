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

    private static Scanner sc = new Scanner(System.in); //Scanner used to read user input from the keyboard
    private static List<String> applicants = new ArrayList<>();//List that sotres applicants loaded from the file
    private static List<Employee> employees = new ArrayList<>();//List that stores employees added manually by the user
    private static List<String> manualApplicants = new ArrayList<>();//List that sotres name typed manually (Used later when mixing with file applicants

    // ======================================================
    //                     MAIN MENU
    // ======================================================
    public static void main(String[] args) { // TODO code application logic here

        System.out.println("");
        System.out.println("   DEPARTMENT STORE EMPLOYEE SYSTEM    ");
        System.out.println("");

        boolean running = true;//Controls when the system should stop

        while (running) { //Main loop that keeps the manu running until the user chooses to exit
            //This is the main menu options//
            System.out.println("\nMAIN MENU");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Sort Employee List");
            System.out.println("4. Generate Employee Hierarchy");
            System.out.println("5. Display Added Employees");
            System.out.println("6. Sort Applicants File");
            System.out.println("7. Exit System");
            System.out.print("Select an option: ");

            String choice = sc.nextLine().trim();// read the user manu choice

            switch (choice) { // handle the user selection

                case "1":
                    addEmployee(); //Add a new employee
                    break;

                case "2":
                    searchEmployee();//Search for an employee by name 
                    break;

                case "3":
                    sortEmployees();//Sort employee alphabetically
                    break;

                case "4":
                    generateHierarchy();//Build a binary tree hierarchy
                    break;  

                case "5":
                    displayAddedEmployees();//Show all employees added
                    break;

                case "6":
                    ApplicantsFromFile();//Load applicants from file
                    sortApplicants();//Sort them 
                    displayFirst20Applicants();// Show the first 20 employees
                    break;
                    
                case "7":   
   
                    System.out.println("Finish programm");
                    running = false;// Stop the loop and exit 
                    break;

                default:
                    System.out.println("Invalid option. Please select 1–7.");
            }
        }
    }

    // ======================================================
    //                  ADD EMPLOYEE
    // ======================================================
    private static void addEmployee() {  //Method to validate when the user add a new employee

        System.out.println("\n--- ADD NEW EMPLOYEE ---");

        System.out.print("Enter employee name: ");//Ask for employee name
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {// Validate empty input 
            System.out.println("Error: Name cannot be empty.");
            return;
        }
        if (!name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {// Validate that the name comtains only letters
            System.out.println("Error: Name must contain only letters. Numbers or symbols are not allowed.");
            return;
        }

        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();//Format that name(Capitalize first letter)

        
        for (Employee e : employees) {//Check if the employee already exists
            if (e.getName().equalsIgnoreCase(name)) {
                System.out.println("Error: An employee with this name already exists.");
                return;
            }
        }
        
           //Ask the user to choose a manager type 
        System.out.println("Select Manager Type:");
        System.out.println("1. Floor Manager");
        System.out.println("2. Assistant Manager");
        System.out.println("3. General Manager");
        System.out.print("Choose option: ");

        String managerChoice = sc.nextLine().trim();
        String managerType = "";

        //Convert user choice into a manager type string
        switch (managerChoice) {
            case "1": managerType = "Floor Manager"; break;
            case "2": managerType = "Assistant Manager"; break;
            case "3": managerType = "General Manager"; break;
            default:
                System.out.println("Error: Invalid Manager Type.");
                return;
        }

        //Ask the user to choose a department
        System.out.println("Select Department:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Home");
        System.out.print("Choose option: ");

        String deptChoice = sc.nextLine().trim();
        Department department;

        //Convert user choice into a department object
        switch (deptChoice) {
            case "1": department = new Department("Electronics"); break;
            case "2": department = new Department("Clothing"); break;
            case "3": department = new Department("Home"); break;
            default:
                System.out.println("Error: Invalid Department.");// is going to display an error if the user enter a invalidad department
                return;
        }

        Employee emp = new Employee(name, managerType, department);//Create the new employee object 
        employees.add(emp);// Add employee to the list
        manualApplicants.add(name);// Also sotre the name as a manual applicant
        
        //Validating user input message 
        System.out.println("\nEmployee added successfully!");
        System.out.println("Name: " + name);
        System.out.println("Manager Type: " + managerType);
        System.out.println("Department: " + department.getName());
    }

    // ======================================================
    //                DISPLAY EMPLOYEES
    // ======================================================
    private static void displayAddedEmployees() { //Method to validate  displaying the added employees

        System.out.println("\n--- EMPLOYEE LIST ---");

        if (employees.isEmpty()) {//if no emplooyees exist, show message
            System.out.println("No employees found.");
            return;
        }

        //Display table header of the list employees
        System.out.println("Total Employees: " + employees.size());
        System.out.println("---------------------------------------");
        System.out.printf("%-20s %-20s %-20s\n", "Name", "Manager Type", "Department");
        System.out.println("---------------------------------------");

        // Print each employee in a formatted table
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
    private static void sortEmployees() { //Method to validate  sort emplyees when the user added a employees

        if (employees.isEmpty()) {//If the list is empty, nothing to sort
            System.out.println("No employees to sort.");
            return;
        }

        System.out.println("\nSorting employees alphabetically (recursive)...");

        Sorter.recursiveSort(employees);// Call the recursive sorting method

        System.out.println("Sorting completed!");// ones the sorting is completed, is going to show a successfully message

        // Display first 20 employees after sorting
        System.out.println("\n--- Display added Employees ---");// is going to show the employees that we already added

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
    private static void searchEmployee() { //Method to validate search the employees from the file or manually
        
        //if no employees exist, searching is impossible 
        if (employees.isEmpty()) {
            System.out.println("\nNo employees available to search.");
            return;
        }

        System.out.print("\nEnter employee name to search: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {// Validate empty input 
            System.out.println("Error: Name cannot be empty.");
            return;
        }

        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();// Format the name for consistent searching 

        System.out.println("\nSearching recursively for: " + formattedName + "...");

        Employee result = Searcher.recursiveSearch(employees, formattedName);// Perform recursive search

        //Display result 
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
    private static void generateHierarchy() { //Method to validate the hierarchy tree (Binary tree)
        
        
        if (employees.isEmpty()) { //If no employees exist, hierarchy ca not be created 
            System.out.println("No employees available to generate hierarchy.");
            return;
        }

        BinaryTree tree = new BinaryTree();

        for (Employee e : employees) { //Insert each employee into the binary tree
            tree.insert(e);
        }

        tree.displayHierarchy(); //Display the hierarchy visually
    }

    // ======================================================
    //           APPLICANTS FROM FILE
    // ======================================================

    private static void ApplicantsFromFile() { //Method to validate the applicants from file

    System.out.println("Java is looking for the file in: " + new java.io.File(".").getAbsolutePath()); //Show where Java is looking for the file
    applicants.clear();// clear previous data

    try (Scanner fileScanner = new Scanner(new java.io.File("Applicants_Form.txt"))) {

        if (fileScanner.hasNextLine()) { //Skip the fisrt line (Header)
            fileScanner.nextLine();
        }

        while (fileScanner.hasNextLine()) { //read each line of the line 
            String line = fileScanner.nextLine().trim();

            if (!line.isEmpty()) {

                String[] parts = line.split(","); // split line by comma
                
                if (parts.length >= 2) { //Ensure at least first and last name exist
                    String firstName = parts[0].trim(); 
                    String lastName = parts[1].trim();

                    String fullName = firstName + " " + lastName;

                    applicants.add(fullName); //Applicants (Added full name) to display on the list
                }
            }
        }

        System.out.println("\nApplicants file loaded successfully!");// is going to display a successfully is input is correct
        System.out.println("Total names found: " + applicants.size());// is going to display the total the names

    } catch (Exception e) {
        System.out.println("Error: Could not read Applicants_Form.txt");// is going to be a error if applicants form is not correct
        System.out.println("Details: " + e.getMessage());
    }
}
    
    // ======================================================
    //          SORT APPLICANTS (RECURSIVE)
    // ======================================================
    private static void sortApplicants() { //Method to validate sort applicants from the file 

        if (applicants.isEmpty()) {// if no applicants were loaded, sorting is impossible
            System.out.println("No applicants loaded. Load the file first.");// the input has to be first, to see the load on tje file
            return;
        }

        System.out.println("\nSorting applicants (recursive)...");

        Sorter.recursiveSortStrings(applicants); // sort the list using recursive method 

        System.out.println("Sorting completed!"); //if everything is correct, its going to display a completed message
    }

    // ======================================================
    //      DISPLAY FIRST 20 APPLICANTS
    // ======================================================
private static void displayFirst20Applicants() { //Method to validate the first 20 applicants 

    //if both lists are empty, nothing to show 
    if (applicants.isEmpty() && manualApplicants.isEmpty()) {
        System.out.println("No applicants loaded.");// is going to display a message if is empty 
        return;
    }

    //Combine file applicants + manually added names 
    List<String> combined = new ArrayList<>();
    combined.addAll(applicants);    // Combined applicants from the file   
    combined.addAll(manualApplicants);   // combined manualApplicants from the user

    Sorter.recursiveSortStrings(combined); // sort the combined list 

    System.out.println("\n--- FIRST 20 APPLICANTS (FILE + MANUAL) ---");

    int limit = Math.min(20, combined.size()); // is going to show a output message with the first 20 applicants(File+Manual)

    for (int i = 0; i < limit; i++) { // Display the fisrt 20 full names
        System.out.println((i + 1) + ". " + combined.get(i));
    }
  }
}