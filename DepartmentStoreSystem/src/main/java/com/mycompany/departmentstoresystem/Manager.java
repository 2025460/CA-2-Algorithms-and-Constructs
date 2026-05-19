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

    private String level;

    public Manager(String name, String managerType, Department department, String level) {
        super(name, managerType, department);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + " | Level: " + level;
    }
}