/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd;

import java.io.*;
import java.util.*;

/**
 *
 * @author Sam Cryan
 */
public class DvdManage {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean more = true;
        HashMap<String, dvd> shelf = load("shelf.txt");
        System.out.println("Load complete. ");
        do {
            System.out.println("Listen closely: Type \"lookup\" to see if your flick is available.");
            
        } while (more == true);
    }

    private static HashMap<String, dvd> load(String fileName) throws FileNotFoundException, IOException {
        HashMap<String, dvd> shelf = new HashMap<>();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
        while (sc.hasNextLine()) {
            shelf.put("a", name_breakdown(sc.nextLine()));
        }
        return shelf;
    }

    private static dvd name_breakdown(String full) {
        String[] splitted = full.split("\"");
        for (int i = 0; i < splitted.length; i++) {
            System.out.println(splitted[i]);
        }
        dvd newby = new dvd(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5]);
        return newby;
    }

}
