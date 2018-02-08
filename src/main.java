import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.util.Random;

public class main {

	public static String word = "";
	public static int guessNumber = 0;
	static Scanner scanner = new Scanner(System.in);
	public static boolean stillGuessing = true;
	public static int wrongGuesses = 0;
	public static boolean repeat = false;
	public static String toString = "";
	public static StringBuilder readout = new StringBuilder();
	public static List<String> guessed = new ArrayList<String>();
	public static String name;
	public static int gamesPlayed;
	public static int gamesLost;
	public static int gamesWon;
	public static float winPercentage;
	public static int leastGuesses;
	public static int mostGueses;
	public static String fileName = "stats.txt";
	
	public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static char[] alphabetRef = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
 	public static void main(String[] args) {
		init();
	}
 	
 	public static void init()
 	{
 		for(int i = 0; i < alphabet.length; i++) {
 			alphabet[i] = alphabetRef[i];
 		}
 		
 		guessed = new ArrayList<String>();
 		
 		
 		
 		
 		ident();
 		System.out.println("");
 		
 		
 		
 		printAlphabet('-');
		beginGame();
		
 	}
	
	public static void ident() {
		toString = "";
		readout = new StringBuilder();
		word = "";
		guessNumber = 0;
		wrongGuesses = 0;
		
		System.out.println("");
		printSpace(12); System.out.println(" ___");
		printSpace(12); System.out.print("(   )                                                                                  \r\n");
		printSpace(12); System.out.print(" | | .-.       .---.     ___ .-.       .--.      ___ .-. .-.       .---.     ___ .-.   \r\n");
		printSpace(12); System.out.print(" | |/   \\     / .-, \\   (   )   \\     /    \\    (   )   '   \\     / .-, \\   (   )   \\  \r\n"); 
		printSpace(12); System.out.print(" |  .-. .    (__) ; |    |  .-. .    ;  ,-. '    |  .-.  .-. ;   (__) ; |    |  .-. .  \r\n"); 
		printSpace(12); System.out.print(" | |  | |      .'`  |    | |  | |    | |  | |    | |  | |  | |     .'`  |    | |  | |  \r\n"); 
		printSpace(12); System.out.print(" | |  | |     / .'| |    | |  | |    | |  | |    | |  | |  | |    / .'| |    | |  | |  \r\n"); 
		printSpace(12); System.out.print(" | |  | |    | /  | |    | |  | |    | |  | |    | |  | |  | |   | /  | |    | |  | |  \r\n"); 
		printSpace(12); System.out.print(" | |  | |    ; |  ; |    | |  | |    | '  | |    | |  | |  | |   ; |  ; |    | |  | |  \r\n"); 
		printSpace(12); System.out.print(" | |  | |    ' `-'  |    | |  | |    '  `-' |    | |  | |  | |   ' `-'  |    | |  | |  \r\n"); 
		printSpace(12); System.out.print("(___)(___)   `.__.'_.   (___)(___)    `.__. |   (___)(___)(___)  `.__.'_.   (___)(___) \r\n"); 
		printSpace(12); System.out.print("                                      ( `-' ;                                          \r\n"); 
		printSpace(12); System.out.print("                                       `.__.                                           \r\n"); 
		printSpace(12); System.out.println("");
				
		System.out.println("                          Created by Matt Bulley - 07 FEB 2018");
		System.out.println("");
		System.out.println("                          (enter \"quit\" to quit the program)");
		printHangmanIdent();
		System.out.println("");
		//System.out.println("Please enter your name");
		//System.out.println("");
		//login(); Work in progress
		difficultySelect(repeat);
	}
	
	public static void login() {
		scanner.reset();
		
		boolean incorrectInput = true;
		String _name = "";
		do {
			System.out.println("");
			
			try {
					_name = (scanner.nextLine());
					incorrectInput = false;
				} catch (Exception wrongInput) {
					System.out.println("");
					System.out.println("Incorrect input! Please enter a valid name");
				}
		} while (incorrectInput);
		
		
		// READING FILE
		// The name of the file to open.
        String _fileName = _name + ".txt";

        // This will reference one line at a time
        String line = null;
        String[] available = new String[466600];
        int i = 0;
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(_fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                available[i] = line;
                System.out.println(i + " " + available[i]);
                i++;
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
           writePlayerStats(_name, _fileName);          
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		
	}
	
	public static void difficultySelect(boolean repeat) {
		printSpace(36); System.out.println("Pick difficulty:");
		printSpace(36); System.out.println("easy, medium, hard (1/2/3)");
		if(repeat)
			scanner.reset();
		
		int difficulty = 0;
		boolean incorrectInput = true;
		
		do {
			System.out.println("");
			
			try {
					difficulty = Integer.parseInt(scanner.nextLine());
					incorrectInput = false;
				} catch (Exception wrongInput) {
					if(!repeat) {
					System.out.println("");
					System.out.println("Incorrect input! Please enter a valid difficulty");
					}
					repeat = false;
				}
		
		} while (incorrectInput);
		
		if(difficulty == 1 || difficulty == 2 || difficulty == 3)
		{
			
			//scanner.close();
			chooseRandomWord(difficulty);
			
		} else {
			System.out.println("");
			System.out.println("Incorrect input! Please enter a valid difficulty");
			difficulty = 0;
			difficultySelect(repeat);
			return;
		}
		
		//scanner.close();
	}
	
	public static String chooseRandomWord(int difficulty)
	{
		switch(difficulty) {
			case 1:
				System.out.println("Picking easy word...");
				break;
			case 2:
				System.out.println("Picking medium word...");
				break;
			case 3:
				System.out.println("Picking hard word...");
				break;
		}
	
		 // The name of the file to open.
        String fileName = "words.txt";

        // This will reference one line at a time
        String line = null;
        String[] available = new String[466600];
        int i = 0;
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                available[i] = line;
              //  System.out.println(i + " " + available[i]);
                i++;
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        Random rand = new Random();
        int randomIndex = rand.nextInt(466546);
        word = available[randomIndex];
     
        // checks the random word is contains only letters, if not randomizes again
        while(!isAlpha(word)) {
        	randomIndex = rand.nextInt(466546);
            word = available[randomIndex];
        }
        
        //easy
        if(difficulty == 1) {
	        while(word.length() < 7) {
	        	randomIndex = rand.nextInt(466546);
	            word = available[randomIndex];
	        }
        }
        // medium
        if(difficulty == 2) {
	        while(word.length() < 5 || word.length() > 7) {
	        	randomIndex = rand.nextInt(466546);
	            word = available[randomIndex];
	        }
        }
        // hard
        if(difficulty == 3) {
	        while(word.length() < 3 || word.length() > 5) {
	        	randomIndex = rand.nextInt(466546);
	            word = available[randomIndex];
	        }
        }
        
        //System.out.println("Selected word: " + word);
        word = word.toLowerCase();
        word = word.replaceAll("\\s+", "").replaceAll("'", "");
   
		return word;
	}
	
	public static boolean isAlpha(String name) {
	    char[] characters = name.toCharArray();

	    for (char character : characters) {
	        if(!Character.isLetter(character)) {
	            return false;
	        }
	    }

	    return true;
	}

	public static void beginGame() {
		System.out.println("");
        //System.out.println("Your word is : " + word);
        
        readout = new StringBuilder();
        char[] characters = word.toCharArray();
        
        for(char character : characters) {
        	readout.append("*");
        	//readout.append(" ");
        }
    
        String toString = readout.toString();
        System.out.println("");
        System.out.println("First guess : " + toString);
        
        while(stillGuessing && wrongGuesses < 7) {
        	char firstGuess = makeGuess(guessNumber);
            System.out.println("");
            checkGuess(firstGuess, readout);
            firstGuess = '\0';
        }
        
        
	}
	
	public static void restart()
	{
		
		System.out.println("");
		System.out.println("Play again? (y/n)");
		
		char answer = '\0';
		answer = scanner.next().charAt(0);
		
		if(answer == 'y') {
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			repeat = true;
			init();
		
		} else {
			scanner.close();
			System.out.println("");
			System.out.println("");
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}
	
	public static char makeGuess(int guessNumber)
	{
		boolean badInput = true;
		char guess = '\0';
		boolean newGuess = true;
		String oldGuess = "";
		
		boolean sameInput = sameInput(guess);
		
			
			while (badInput)
			{
				guess = scanner.next().charAt(0);
				guess = Character.toLowerCase(guess);
				oldGuess = Character.toString(guess);
				
				if(guessed.contains(oldGuess)) {
					newGuess = false;
					System.out.println("");
					System.out.println("Already guessed! Try Again.");
				} else if(Character.isLetter(guess)) {
					badInput = false;
					
				} else {
					System.out.println("Bad input! Try Again.");
					System.out.println("");
				}
			}
			
			
			
			System.out.println("");
			printAlphabet(guess);
			sameInput = sameInput(guess);
			if(sameInput) {
				System.out.println("");
				System.out.println("Already tried! Pick another letter.");
			}
		
		
		guessed.add(oldGuess);
		return guess;
	}
	
	public static void printAlphabet(char guess)
	{
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i] == guess) {
				alphabet[i] = '_';
			}
			System.out.print(alphabet[i] + "");
			System.out.print("");
		}
		
		System.out.println("");
	}

	public static boolean sameInput(char guess)
	{
		boolean alreadyTried = false;
		
		
		
		return alreadyTried;
	}
	
	public static String checkGuess(char guess, StringBuilder readout)
	{
		toString = readout.toString();
		char[] characters = word.toCharArray();
		boolean correct = false;
		
		for(int i = 0; i < characters.length; i++) {
			
			if(characters[i] == guess) {
				//readout.deleteCharAt(i);
        		readout.setCharAt(i, guess);
        		correct = true;
        	}
        }
		
		toString = readout.toString();
		
		if(!correct) {
			wrongGuesses++;
			
			switch (wrongGuesses) {
				case 1:
					printHangman1();
					break;
				case 2:
					printHangman2();
					break;
				case 3:
					printHangman3();
					break;
				case 4:
					printHangman4();
					break;
				case 5:
					printHangman5();
					break;
				case 6:
					printHangman6();
					break;
				case 7:
					printHangman7();
					System.out.println("");
					System.out.println("You died! To : " + word);
					restart();
					break;
				default:
				
				
			} 
			System.out.println("");
			System.out.println((7 - wrongGuesses) + " guesses left.");
		} else {
			if(toString.equals(word))
			{
				
				System.out.println("");
				System.out.println(":::: YOU WIN! ::::");
				System.out.println("");
				System.out.println("with: " + toString);
				restart();
			}
			
			
		}
		System.out.println("");
        System.out.println(toString);
		
		return toString;
	}
	
	public static void printHangman1() 
	{
		// first wrong
        System.out.println("__ ___");
        System.out.println("|    |");
	
	}
	
	public static void printHangman2() {
		// second wrong
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("__|___");
        System.out.println("|    |");
	}
	
	public static void printHangman3() {
		// third wrong
        System.out.println("  _______");
        System.out.println("  |     |");
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("__|___");
        System.out.println("|    |");
	}
	
	public static void printHangman4() {
		// fourth wrong
        System.out.println("  _______");
        System.out.println("  |     |");
        System.out.println("  |     O");
        System.out.println("  |     ");
        System.out.println("  |    ");
        System.out.println("  |");
        System.out.println("__|___");
        System.out.println("|    |");
	}
	
	public static void printHangman5() {
		 // fifth
        System.out.println("  _______");
        System.out.println("  |     |");
        System.out.println("  |     O");
        System.out.println("  |    / \\");
        System.out.println("  |    ");
        System.out.println("  |");
        System.out.println("__|___");
        System.out.println("|    |");
	}
	
	public static void printHangman6() {
		// sixth 
        System.out.println("  _______");
        System.out.println("  |     |");
        System.out.println("  |     O");
        System.out.println("  |    /|\\");
        System.out.println("  |     ");
        System.out.println("  |");
        System.out.println("__|___");
        System.out.println("|    |"); 
	}
	
	public static void printHangman7() {
		// Final
		System.out.println("  _______");
		System.out.println("  |     |");
	    System.out.println("  |     O");
		System.out.println("  |    /|\\");
		System.out.println("  |    / \\");
		System.out.println("  |");
		System.out.println("__|___");
		System.out.println("|    |"); 
	}
	
	public static void printHangmanIdent() {
		// Final
		printSpace(36); System.out.println("  _______");
		printSpace(36); System.out.println("  |     |");
		printSpace(36); System.out.println("  |     O");
		printSpace(36); System.out.println("  |    /|\\");
		printSpace(36); System.out.println("  |    / \\");
		printSpace(36); System.out.println("  |");
		printSpace(36); System.out.println(" _|___");
		printSpace(36); System.out.println("|    |"); 
	}
	
	public static void printSpace(int spaces) {
		switch(spaces) {
			case 36:
				System.out.print("                                    ");
				break;
			case 12:
				System.out.print("            ");
				break;
		}
		
	
	}


	public static void writePlayerStats(String _name, String _fileName) {
		
		try {
			WriteFile data = new WriteFile(_fileName, true);
			data.WriteToFile(_name);
			data.WriteToFile("Games Played: ");
			data.WriteToFile("Games Lost: ");
			data.WriteToFile("Games Won: ");
			data.WriteToFile("Win Percentage : ");
			data.WriteToFile("Least Guesses: ");
			data.WriteToFile("Most Guesses: ");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("Text File Written to");
		
	}
	
}
