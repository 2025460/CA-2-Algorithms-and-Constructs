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

    private String name;
    private String managerType;
    private Department department;

    public Employee(String name, String managerType, Department department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getManagerType() {
        return managerType;
    }

    public Department getDepartment() {
        return department;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return name + " | " + managerType + " | " + department.getName();
    }
}
 
    

