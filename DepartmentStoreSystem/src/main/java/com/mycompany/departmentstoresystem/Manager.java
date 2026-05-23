/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.departmentstoresystem;

/**
 *
 * @author emcav
 */
public class Manager extends Employee {

    private String level; // Extra attribute only for managers

    public Manager(String name, String managerType, Department department, String level) {// Constructor: creates a Manager and also calls Employee constructor
        super(name, managerType, department); // Set inherited fields
        this.level = level;// set manager level 
    }

    public String getLevel() { // returns the manager level
        return level;
    }

    public void setLevel(String level) {// updates the manager level
        this.level = level;
    }

    @Override
    public String toString() {// returns a readable string with all manager info
        return super.toString() + " | Level: " + level;
    }
}