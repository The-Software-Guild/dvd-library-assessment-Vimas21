import java.io.*;
import java.util.*;

public class library {
	
	public static void main() throws FileNotFoundException {
		load("DVD.txt");
	}
	
	private static HashMap<String, DVD> load(String fileName) throws FileNotFoundException{
		HashMap<String, DVD> shelf = new HashMap<>();
		Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
		while(sc.hasNextLine()){
			System.out.println(sc.nextLine());
		}
		return shelf;
	}
	
}

