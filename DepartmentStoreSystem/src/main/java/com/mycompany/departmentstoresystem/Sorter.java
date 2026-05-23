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
public class Sorter {

    // ======================================================
    //      RECURSIVE SORT FOR EMPLOYEE OBJECTS
    // ======================================================
    public static void recursiveSort(List<Employee> list) {
        if (list == null || list.size() <= 1) return; //if list is empty or has only 1 elemnt, nothing to sort 
        recursiveInsertion(list, list.size()); // start recursive insertion sort 
    }

    private static void recursiveInsertion(List<Employee> list, int n) { // recursive insertion sort for employee objects

        if (n <= 1) return; // if this case is when only 1 elemnt remains

        recursiveInsertion(list, n - 1);// Sort first n-1 elements

        Employee last = list.get(n - 1);// Last element to insert into sorted part
        int j = n - 2;

        while (j >= 0 && list.get(j).getName().compareToIgnoreCase(last.getName()) > 0) {// Shift elements to the right until correct position is found
            list.set(j + 1, list.get(j));
            j--;
        }

        list.set(j + 1, last); // Insert the element in its correct position
    }

    // ======================================================
    //      RECURSIVE SORT FOR STRINGS (APPLICANTS FILE)
    // ======================================================
    public static void recursiveSortStrings(List<String> list) {
        if (list == null || list.size() <= 1) return;
        recursiveInsertionStrings(list, list.size());
    }

    private static void recursiveInsertionStrings(List<String> list, int n) { // Recursive insertion sort for Strings

        if (n <= 1) return; // if this case is when only 1 elemnt remains

        recursiveInsertionStrings(list, n - 1); // Sort first n-1 elements

        String last = list.get(n - 1); // Last string to insert
        int j = n - 2;

        while (j >= 0 && list.get(j).compareToIgnoreCase(last) > 0) { // Shift elements until correct alphabetical position is found
            list.set(j + 1, list.get(j));
            j--;
        }

        list.set(j + 1, last);// Insert string in correct position
    }
}
    
