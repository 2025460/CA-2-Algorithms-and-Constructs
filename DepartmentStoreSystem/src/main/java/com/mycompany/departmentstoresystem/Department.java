/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.departmentstoresystem;

/**
 *
 * @author emcav
 */
public class Department {

    private String name;// This variable stores the name of the department

    public Department(String name) {// called when creating a new department object
        this.name = name; // assign the provided name to the department.
    }

    public String getName() { // getter method, returns the department name.
        return name;
    }

    public void setName(String name) { //setter method, allows updating the department name
        this.name = name;// useful if the department name needs to be changed later.
    }
}
