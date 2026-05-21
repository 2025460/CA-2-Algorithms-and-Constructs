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
    
public class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    
    public void insert(Employee employee) {

        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();

            if (current.left == null) {
                current.left = newNode;
                return;
            } else q.add(current.left);

            if (current.right == null) {
                current.right = newNode;
                return;
            } else q.add(current.right);
        }
    }

    
    public void displayHierarchy() {

        if (root == null) {
            System.out.println("Hierarchy is empty.");
            return;
        }

        System.out.println("\n--- EMPLOYEE HIERARCHY (BFS Traversal) ---");

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();

            System.out.println("- " + current.employee.getName() +
                    " | " + current.employee.getManagerType() +
                    " | " + current.employee.getDepartment().getName());

            if (current.left != null) q.add(current.left);
            if (current.right != null) q.add(current.right);
        }

        System.out.println("\n--- TREE STATISTICS ---");
        System.out.println("Total Nodes: " + countNodes());
        System.out.println("Tree Height: " + getHeight());
        System.out.println("------------------------");
    }

    
    public int countNodes() {
        return countNodesRecursive(root);
    }

    private int countNodesRecursive(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodesRecursive(node.left) + countNodesRecursive(node.right);
    }

    
    public int getHeight() {
        return heightRecursive(root);
    }

    private int heightRecursive(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }
}