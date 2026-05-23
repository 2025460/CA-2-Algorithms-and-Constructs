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

    Employee employee;// stores the employee object held in this node 
    TreeNode left;// left child the binary tree 
    TreeNode right;//right child the binary tree 

    public TreeNode(Employee employee) {// this one creates a new tree node containing an employee and initializies both childen as null
        this.employee = employee;// set the employee stored in this node 
        this.left = null; // no left child yet
        this.right = null;// no right child yet
    }
}
