/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.I_Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Util;

/**
 *
 * @author HP
 */
public class Menu implements I_Menu{
    private final Scanner scanner;
    private final List<String> menuItems;

    public Menu() {
        super();
        scanner = new Scanner(System.in);
        menuItems = new ArrayList<>();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        menuItems.add(s);
    }

    @Override
    public void showMenu() {
        System.out.println("==== EVENT MANAGEMENT ====");
        for (String item : menuItems) {
            System.out.println(item);
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        System.out.print(welcome);
        return scanner.nextLine().trim().equalsIgnoreCase("N");
    }

    @Override
    public int getChoice() {
        Util ut = new Util();
        return ut.getInteger("Enter your choice: ","Please input from 1 to " + menuItems.size(), 1, menuItems.size());
    }
}
