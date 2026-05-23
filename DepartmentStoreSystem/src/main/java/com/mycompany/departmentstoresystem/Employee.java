/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.departmentstoresystem;

/**
 *
 * @author emcav
 */
public class Employee {

    private String name;// stores the employees full name
    private String managerType;// stores the type of manager this employee 
    private Department department;// stores the department object this employee belongs to

    public Employee(String name, String managerType, Department department) {//  this one receives the employee name, manager type and department
        this.name = name; //set employee name
        this.managerType = managerType;// set manager type
        this.department = department;// set department object
    }

    
    public String getName() { //this is used by other  classes to read the name safely
        return name;// returns the employee name
    }

    public String getManagerType() { 
        return managerType; // returns to the manager type
    }

    public Department getDepartment() { //this allows access to department details like its name
        return department; //returns  the department object 
    }

    
    public void setName(String name) { //updates the employee name
        this.name = name; // useful if the name needs to be corrected or changed
    }

    public void setManagerType(String managerType) { // this method , updates  the manager type 
        this.managerType = managerType;
    }

    public void setDepartment(Department department) { // updates the department object 
        this.department = department; // allows to add a new employee
    }

    @Override
    public String toString() {
        return name + " | " + managerType + " | " + department.getName(); // returns a formatted string representation of the employee
    }
}
 
    

