/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.departmentstoresystem;

/**
 *
 * @author emcav
 */
public class TreeNode {

    Employee employee;
    TreeNode left;
    TreeNode right;

    public TreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}
