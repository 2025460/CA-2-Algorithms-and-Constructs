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

    public static Employee recursiveSearch(List<Employee> list, String name) {
        return search(list, name, 0);
    }

    private static Employee search(List<Employee> list, String name, int index) {

        if (index >= list.size()) return null;

        if (list.get(index).getName().equalsIgnoreCase(name)) {
            return list.get(index);
        }

        return search(list, name, index + 1);
    }
}

