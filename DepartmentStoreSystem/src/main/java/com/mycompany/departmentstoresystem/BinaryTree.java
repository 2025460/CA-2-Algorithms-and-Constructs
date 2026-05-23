/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.departmentstoresystem;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author emcav
 */
    
public class BinaryTree { // the root node of the binary tree 

    private TreeNode root; //if the root is null, the tree is empty

    public BinaryTree() { // Constructor initializez an empty tree
        this.root = null;
    }

    //Inserts a new employee into the binary tree.
    //This tree uses a "level-order insertion" (BFS style)
    //meaning new nodes fill from left to right, top to bottom
    
    public void insert(Employee employee) {

        TreeNode newNode = new TreeNode(employee); // Create a new tree node containing the employee

        if (root == null) { // If the tree is empty, the new node becomes the root 
            root = newNode;
            return;
        }

        Queue<TreeNode> q = new LinkedList<>(); // Queue used to perform  a Breadth-First Search (BFS)\
        q.add(root); //Start BFS from the root

        while (!q.isEmpty()) { // Loop until wefind an empty spot for the new node 
            TreeNode current = q.poll();// remove the next node from the queue

            if (current.left == null) {// check if the left child is empty
                current.left = newNode;// you must insert here
                return;
            } else {
                q.add(current.left);// explore left subtree
            }
            if (current.right == null) {// check if the right child is empty
                current.right = newNode; // you must insert here 
                return;
            } else { 
                q.add(current.right);
        }
    }
  }

    // Prints the hierarchy of employees using BFS traversal
    // BFS prints node level by level, which is ideal for hierarch
    public void displayHierarchy() {

        if (root == null) {// if the tree has no nodes, show a message 
            System.out.println("Hierarchy is empty.");
            return;
        }

        System.out.println("\n--- EMPLOYEE HIERARCHY (BFS Traversal) ---");

        Queue<TreeNode> q = new LinkedList<>();// Queue for BFS traversal
        q.add(root);

        while (!q.isEmpty()) { // process nodes level by level
            TreeNode current = q.poll(); // get the next node

            System.out.println("- " + current.employee.getName() + // print employee details stored in this node
                    " | " + current.employee.getManagerType() +
                    " | " + current.employee.getDepartment().getName());

            // Add children to the queue if they exist
            if (current.left != null) q.add(current.left);
            if (current.right != null) q.add(current.right);
        }
           
            
        System.out.println("\n--- TREE STATISTICS ---");// After printing the hierarchy, show tree statistics
        System.out.println("Total Nodes: " + countNodes());// its show the total nodes 
        System.out.println("Tree Height: " + getHeight()); // its show the tree height 
        System.out.println("------------------------");
    }

    
    public int countNodes() { // Public method that starts the recursive node count
        return countNodesRecursive(root);
    }

    private int countNodesRecursive(TreeNode node) { // Recursive helper method to count nodes in the tree.
        if (node == null) return 0; // this case show if the node is null, return 0.
        return 1 + countNodesRecursive(node.left) + countNodesRecursive(node.right);
    }

    
    public int getHeight() { //Public method that starts the recursive height calculation
        return heightRecursive(root);
    }

    private int heightRecursive(TreeNode node) { // Recursive helper method to compute the height of the tree.
        if (node == null) return 0; //This case empty tree has height 0.
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right)); // height is 1+ the height of the taller subtree
    }
}