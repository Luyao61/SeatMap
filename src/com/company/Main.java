package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please enter the input file path.");
        }
        // create input and output file path
        String path = args[0];
        String output_path = path.split("\\.")[0] + "_out.txt";

        BufferedReader reader = new BufferedReader(new FileReader(path));
        BufferedWriter output_file = new BufferedWriter(new FileWriter(output_path, false));
        try {
            String line;
            Theater theater = new Theater();
            // read the input file line by line
            while ((line = reader.readLine()) != null) {
                String[] ln = line.split("\\s+");
                Order current_order = new Order(ln[0], Integer.parseInt(ln[1]));

                theater.processOrder(current_order);

                output_file.write(current_order.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
            output_file.close();
        }
        System.out.println(output_path);
    }
}
