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
        if (list == null || list.size() <= 1) return;
        recursiveInsertion(list, list.size());
    }

    private static void recursiveInsertion(List<Employee> list, int n) {

        if (n <= 1) return;

        recursiveInsertion(list, n - 1);

        Employee last = list.get(n - 1);
        int j = n - 2;

        while (j >= 0 && list.get(j).getName().compareToIgnoreCase(last.getName()) > 0) {
            list.set(j + 1, list.get(j));
            j--;
        }

        list.set(j + 1, last);
    }

    // ======================================================
    //      RECURSIVE SORT FOR STRINGS (APPLICANTS FILE)
    // ======================================================
    public static void recursiveSortStrings(List<String> list) {
        if (list == null || list.size() <= 1) return;
        recursiveInsertionStrings(list, list.size());
    }

    private static void recursiveInsertionStrings(List<String> list, int n) {

        if (n <= 1) return;

        recursiveInsertionStrings(list, n - 1);

        String last = list.get(n - 1);
        int j = n - 2;

        while (j >= 0 && list.get(j).compareToIgnoreCase(last) > 0) {
            list.set(j + 1, list.get(j));
            j--;
        }

        list.set(j + 1, last);
    }
}
    
