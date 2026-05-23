/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.departmentstoresystem;
import java.util.List;
/**
 *
 * @author emcav
 */
public class Searcher {

    public static Employee recursiveSearch(List<Employee> list, String name) { // public method that starts the recursive search
        return search(list, name, 0); //is going to search from index 0
    }

    private static Employee search(List<Employee> list, String name, int index) {// Recursive search method from list, name,index.

        if (index >= list.size()) return null; // this case if the index goes past the list size, not found

        if (list.get(index).getName().equalsIgnoreCase(name)) { // check if the current employee matches with the name 
            return list.get(index);// found return employee
        }

        return search(list, name, index + 1);// recursive, move to next index
    }
}

