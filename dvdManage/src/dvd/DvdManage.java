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
        String choice = ""; // We will use choice across the board to measure input.
        Scanner inputCheck = new Scanner(System.in);
        
        System.out.println("Welcome to the DVD manager. Please enter the name of the txt file you wish to access.");
        choice = inputCheck.nextLine();
        String pastFile = choice;
        HashMap<String, Dvd> shelf = load(pastFile); // Load takes a file ID and returns a Hashmap of all DVDs available.
        System.out.println("Load complete. ");
        
        do {
            System.out.println();
            System.out.println("Listen closely: Type \"search\" to see if your flick is available.");
            System.out.println("Type \"all\" to see the name of each and every flick.");
            System.out.println("Type \"details \" if you want to see the details on an available flick.");
            System.out.println("Type \"add\" to add a new movie.");
            System.out.println("Type \"remove\" to remove an existing movie.");
            System.out.println();
            choice = inputCheck.nextLine();
            
            if(choice.equalsIgnoreCase("search")){
                search(shelf, inputCheck);
            }
            else if(choice.equalsIgnoreCase("all")){
                allPrint(shelf);
            }
            else if(choice.equalsIgnoreCase("details")){
                details(shelf, inputCheck);
            }
            else if(choice.equalsIgnoreCase("add")){
                addMovie(shelf, inputCheck);
            }
            
            else if(choice.equalsIgnoreCase("remove")){
                removeMovie(shelf, inputCheck);
            }
            else{
                System.out.println("Sorry, didn't catch that.");
            }
            
            System.out.println("Click any key to continue.");
            inputCheck.nextLine();
            System.out.println("Please type \"continue\" if you would like to continue");
            choice = inputCheck.nextLine();
            
            if(!choice.equalsIgnoreCase("continue")){
                   more = false;
            }
        } while (more == true);
        
        System.out.println("Before we finish up, I need to know where to save this file to.");
        System.out.println("Type \"same\" if you want it to save where you loaded from.");
        System.out.println("Type \"new\" if you want it to save to a new file.");
        System.out.println("Type \"skip\" if you don't want to save it.");
        boolean answerGiven=false;
        
        do{
            choice = inputCheck.nextLine();
            if(choice.equalsIgnoreCase("same")){
                save(shelf, pastFile);
                answerGiven=true;
            }
            else if(choice.equalsIgnoreCase("new")){
                System.out.println("What would you like to name the file?");
                String newFile = inputCheck.nextLine();
                save(shelf, newFile);
                answerGiven=true;
            }
            else if(choice.equalsIgnoreCase("skip")){
                answerGiven=true;
            }
            else{
                System.out.println("Sorry, I didn't catch that.");
            }
        } while(!answerGiven);
    }

    private static HashMap<String, Dvd> load(String fileName) throws FileNotFoundException, IOException {
        HashMap<String, Dvd> shelf = new HashMap<>();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
        
        while (sc.hasNextLine()) {
            Dvd freshDvd = nameBreakdown(sc.nextLine());
            shelf.put(freshDvd.title, freshDvd);
        }
        
        return shelf;
    }
    
    private static void save(HashMap<String, Dvd> shelf, String fileName) throws IOException{
        File toFile = new File(fileName); // Checks the file, and makes a new one if nescessary.
        Set<String> keys = shelf.keySet();
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        
        for(String k : keys){
            Dvd currentDvd = shelf.get(k);
            out.println(k+ "\"" + currentDvd.releaseDate + "\"" + currentDvd.mpaa + "\"" + currentDvd.director + "\""  + currentDvd.studio + "\"" + currentDvd.notes);
        }
        out.flush();
        out.close();
    }

    private static Dvd nameBreakdown(String full) {
        String[] splitted = full.split("\"");
        //for (int i = 0; i < splitted.length; i++) {
            //System.out.println(splitted[i]);
        //}
        // The order this is released is: Title, date, rating, director, studio, notes.
        Dvd newby = new Dvd(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5]);
        return newby;
    }
    
    private static void search(HashMap<String, Dvd> library, Scanner input){
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
    
    private static void allPrint(HashMap<String, Dvd> shelf){
        System.out.println("Okay, here we go!");
        Set<String> keys = shelf.keySet();
        
        for(String k : keys){
            System.out.println(k);
        }
    }
    
    private static void details(HashMap<String,Dvd> shelf, Scanner input){
        System.out.println("Okay, what movie do we want?");
        String choice = input.nextLine();
        System.out.println("Let me look that up...");
        
        if(shelf.containsKey(choice)){
            Dvd discOfChoice = shelf.get(choice);
            System.out.println(discOfChoice.title + " was released on " +
                    discOfChoice.releaseDate + ". It was directed by " + discOfChoice.director + " at " + discOfChoice.studio +
                    ". It is rated " + discOfChoice.mpaa + ".");
            System.out.println("We have these notes on file: " + discOfChoice.notes);
        }
        else{
            System.out.println("That movie wasn't found. Try the \"search\" feature.");
        }
    }
    
    private static void addMovie(HashMap<String,Dvd> shelf, Scanner input){
        System.out.println("Alright, let's add a movie! First, I'll need a name.");
        String name = input.nextLine();
        System.out.println("When was it released?");
        String date = input.nextLine();
        System.out.println("Who directed it?");
        String director = input.nextLine();
        System.out.println("Where was it filmed?");
        String studio = input.nextLine();
        System.out.println("Alright, what was it's MPAA rating?");
        String rating = input.nextLine();
        System.out.println("Any other notes? Input them here.");
        String notes = input.nextLine();
        shelf.put(name, new Dvd(name, date, rating, director, studio, notes));
        System.out.println("Cool. I've added it to the shelf.");
    }

    public static void removeMovie(HashMap<String,Dvd> shelf, Scanner input){
        System.out.println("What movie would you like to remove?");
        String choice = input.nextLine();
        System.out.println("Searching for movie...");
        
        if(shelf.containsKey(choice)){
            shelf.remove(choice);
            System.out.println("Got it- movie removed.");
        }
        else{
            System.out.println("Sorry, I couldn't find that movie.");
        }
    }
}
