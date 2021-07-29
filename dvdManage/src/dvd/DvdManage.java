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
        String choice = "";
        Scanner inputCheck = new Scanner(System.in);
        
        HashMap<String, dvd> shelf = load("shelf.txt");
        System.out.println("Load complete. ");
        
        do {
            System.out.println();
            System.out.println("Listen closely: Type \"search\" to see if your flick is available.");
            System.out.println("Type \"all\" to see the name of each and every flick.");
            System.out.println("Type \"details \" if you want to see the details on an available flick.");
            System.out.println();
            System.out.println();
            choice = inputCheck.nextLine();
            
            if(choice.equalsIgnoreCase("search")){
                search(shelf, inputCheck);
            }
            
            if(choice.equalsIgnoreCase("all")){
                allPrint(shelf);
            }
            
            if(choice.equalsIgnoreCase("details")){
                details(shelf, inputCheck);
            }
            
            inputCheck.nextLine();
        } while (more == true);
    }

    private static HashMap<String, dvd> load(String fileName) throws FileNotFoundException, IOException {
        HashMap<String, dvd> shelf = new HashMap<>();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
        while (sc.hasNextLine()) {
            dvd freshDvd = name_breakdown(sc.nextLine());
            shelf.put(freshDvd.title, freshDvd);
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
    
    private static void search(HashMap<String, dvd> library, Scanner input){
        System.out.println("Okay, what are we looking for?");
        String choice = input.nextLine();
        System.out.println("Alright, searching....");
        
        if(library.containsKey(choice)){
            System.out.println("Got it! " + choice + " is available.");
        }
        else{
            System.out.println("Sorry, I didn't find " + choice + ".");
        }
    }
    
    private static void allPrint(HashMap<String, dvd> shelf){
        System.out.println("Okay, here we go!");
        Set<String> keys = shelf.keySet();
        
        for(String k : keys){
            System.out.println(k);
        }
    }
    
    private static void details(HashMap<String,dvd> shelf, Scanner input){
        System.out.println("Okay, what movie do we want?");
        String choice = input.nextLine();
        System.out.println("Let me look that up...");
        
        if(shelf.containsKey(choice)){
            dvd discOfChoice = shelf.get(choice);
            System.out.println(discOfChoice.title + " was released on " +
                    discOfChoice.releaseDate + ". It was directed by " + discOfChoice.director + " at " + discOfChoice.studio +
                    ". It is rated " + discOfChoice.mpaa + ".");
            System.out.println("We have these notes on file: " + discOfChoice.notes);
        }
        else{
            System.out.println("That movie wasn't found. Try the \"search\" feature.");
        }
    }

}
