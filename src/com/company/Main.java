package com.company;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static List<Order> ans = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please enter the input file path.");
        }
        String path = args[0];
        System.out.println(path);
        BufferedReader reader = new BufferedReader(new FileReader(path));
        try {
            String line;
            Theater theater = new Theater();
            while ((line = reader.readLine()) != null) {
                // Todo
                String[] ln = line.split("\\s+");
                Order current_order = new Order(ln[0], Integer.parseInt(ln[1]));
                theater.processOrder(current_order);
                System.out.println(current_order);
            }
            theater.helper();
            System.out.println();
            theater.helper2();

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}