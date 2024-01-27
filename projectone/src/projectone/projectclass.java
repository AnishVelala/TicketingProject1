package projectone;


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.InputMismatchException;
import java.util.LinkedList;





public class projectclass {
	
	
	 //loads the seating arrangement data from a file and populates the auditorium array.
	 private static final char[][] auditorium = new char[10][26]; // To store the seating arrangement
	
	  public static  void loadAuditorium ( String fileName ) {
		  BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		
		 String line;
		        int row = 0;
	            while ((line = reader.readLine()) != null) {
	                // Convert the line into an array of characters (rowChars)
	                char[] rowChars = line.toCharArray();
	                    // Loop through each character in the rowChars array
	                for (int col = 0; col < rowChars.length; col++) {
	                    auditorium[row][col] = rowChars[col];
	                }
	                row++;
	            }
	            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	  
	  public static  void initializeArray ( char[][] auditorium ) {
		  
		  
		  char defaultChar = '.';
		// Loop through each row and column of the auditorium array
	        for (int row = 0; row < 10; row++) {
	            for (int col = 0; col < 26; col++) {
	            	 // Initialize the current seat with the default character
	                auditorium[row][col] = defaultChar;
	            }
	        }
	  
	  }
	// Displays the seating arrangement in the auditorium.
public static  void displaySeats ( char[][] auditorium ) {
		  
	 System.out.println(" ABCDEFGHIJKLMNOPQRST");
		  
	        for (int row = 0; row < 10; row++) {
	        	System.out.print(row + 1);
	            for (int col = 0; col < 26; col++) {
	            	System.out.print(auditorium[row][col]);
	            }
	            System.out.println();
	        }
	        
	  }
	    
	  public static void main(String[] args) {
	    	        
	    	        //////initializeArray ( auditorium );
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.print("Enter the filename for the auditorium: ");
	    	        String filename = scanner.nextLine();
	    	        
	    	        // Load the auditorium data from the file
	    	        loadAuditorium(filename);
	    	        
	    	        
	    	        int choice = 0;
	    	        do {
	    	            displayReport( auditorium);
	    	            displayMenu();
	    	            try {
	    	            choice = scanner.nextInt();
	    	            
	    	            } catch (InputMismatchException e) {
	    	                // Handle the case where the user enters a non-integer input
	    	            }
	    	            // Process the user's choice based on the selected menu option

	    	            switch (choice) {
	    	                case 1:
	    	                    reserveSeats(scanner);
	    	                    break;
	    	                case 2:
	    	                    break;
	    	                default:
	    	                 // Default case: Handle invalid choices
	    	                    System.out.println("no seats available");
	    	            }
	    	        } while (choice != 2);
	    	        
	    	        // Generate and display the report
	    	        
	    	        displayReport( auditorium);
	    	        // Write the updated auditorium data back to the file
	    	        
	    	        
	    	        scanner.close();
	    	    }
	    	  
	    	    
	    	    private static void displayMenu() {
	    	        // Display the main menu
	    	        // Implement this function
	    	        System.out.println("1. Reserve Seats");
	    	        System.out.println("2. Exit");

	    	    }
	    	    
	    	    private static void reserveSeats(Scanner scanner) {
	    	        // Implement the seat reservation logic here
	    	    	
	    	    	displaySeats(auditorium);  // Display the current seating arrangement
	    	    	int rowNum=0;

	    	        // Prompt for user input
	    	        System.out.print("Row number");
	    	        
	    	        try {
	    	        	  rowNum = scanner.nextInt() - 1; 
	    	            
	    	            } catch (InputMismatchException e) {
	    	            	
	    	               
	    	            }
	    	        
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (rowNum < 0 || rowNum >= auditorium.length) {
	    	            System.out.println("no seats available");
	    	            return;
	    	        }

	    	        System.out.print(" Starting seat letter ");
	    	        char startSeat = scanner.nextLine().charAt(0);

	    	        // Validate the seat letter
	    	        if (startSeat < 'A' || startSeat >= 'A' + auditorium[0].length) {
	    	            System.out.println("no seats available");
	    	            return;
	    	        }

	    	        System.out.print(" Number of adult tickets  ");
	    	        int numAdult = scanner.nextInt();
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (numAdult < 0) {
	    	            System.out.println("Invalid number of adult tickets.");
	    	            return;
	    	        }

	    	        System.out.print(" Number of child tickets  ");
	    	        int numChild = scanner.nextInt();
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (numChild < 0) {
	    	            System.out.println("Invalid number of child tickets.");
	    	            return;
	    	        }

	    	        System.out.print(" Number of senior tickets  ");
	    	        int numSenior = scanner.nextInt();
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (numSenior < 0) {
	    	            System.out.println("Invalid number of senior tickets.");
	    	            return;
	    	        }
	    	        
	    	        // Check seat availability and reserve seats accordingly

	    	        if (AreSeatsAvailable( rowNum,  startSeat, numAdult + numChild +  numSenior ))
	    	        	
	    	        	bookAvailableSeats( rowNum,  startSeat,  numAdult,  numChild,  numSenior);
	    	        
	    	        	else
	    	        		
	    	        		findbestAvailableseat( auditorium,  rowNum,  numAdult,  numChild, numSenior,scanner);
	    	        
	    	        // Write the updated auditorium data back to the file
	    	        writeToFile(auditorium);
	    	    	
	    	    }

	    	    public static  boolean AreSeatsAvailable(int rowNum, char startSeatLetter, int numSeats ) {
	    	    	// Convert the seat letter to its corresponding column index
	    	   	int column = convertSeatLettertoIndex(startSeatLetter);
	    	   
	    	    for (int i = column; i < column + numSeats; i++) {
	    	        // Check if the current seat is already reserved for an adult ('A'), child ('C'), or senior ('S')

	    	    	if  ( auditorium[rowNum][i] == 'A'|| auditorium[rowNum][i] ==  'C'|| auditorium[rowNum][i] == 'S')
	    	    		// Seats are not available if any of them are already reserved
	    	    			return false;
	    	    }
	    	    return true;
	    	    
	    	    }
	    	    
	    	    public static void bookAvailableSeats(int rowNum, char startSeatLetter, int numAdult, int numChild, int numSenior) {
	    	        int column = convertSeatLettertoIndex(startSeatLetter);
	    	    
	    	        int numSeats = numAdult + numChild + numSenior;
	    	        
	    	        // Check if there are enough available seats in the row
	    	        if (column + numSeats > auditorium[rowNum].length) {
	    	            System.out.println("no seats available");
	    	            return;
	    	        }

	    	        for (int i = column; i < column + numSeats; i++) {
	    	            if (auditorium[rowNum][i] == '.') { // Check if the seat is available
	    	                if (numAdult > 0) {
	    	                    auditorium[rowNum][i] = 'A'; // Reserve an adult seat
	    	                    numAdult--;
	    	                } else if (numChild > 0) {
	    	                    auditorium[rowNum][i] = 'C'; // Reserve a child seat
	    	                    numChild--;
	    	                } else if (numSenior > 0) {
	    	                    auditorium[rowNum][i] = 'S'; // Reserve a senior seat
	    	                    numSenior--;
	    	                }
	    	            }
	    	        }
	    	        
	    	        // Handle cases where there are more adults, children, or seniors than available seats
	    	        if (numAdult > 0 || numChild > 0 || numSenior > 0) {
	    	            System.out.println("no seats available");
	    	        }
	    	    }
	    	    	    	    
	    	    public static void displayReport(char[][] auditorium) {
	    	    	
	    	    	int totalSeats=  getMaxRows(auditorium) * getMaxCols(auditorium) ;
	    	    	int totalTickets=0; // Initialize the total number of tickets

	    	    	int totalAdultTickets=0;  // Initialize the total number of adult tickets

	    	    	int totalChildTickets=0; // Initialize the total number of child tickets

	    	    	int totalSeniorTickets=0;  // Initialize the total number of senior tickets
	    	    	
	    	    	for (int row = 0; row < 10; row++) {
	    	            for (int col = 0; col < 26; col++) {
	    	            	// Check if the seat is reserved as an adult, child, or senior ticket

	    	                if (auditorium[row][col] == 'A'|| auditorium[row][col] ==  'C'|| auditorium[row][col] == 'S')
	    	                	totalTickets++;
	    	                if (auditorium[row][col] == 'A')
	    	                	totalAdultTickets++;
	    	                if (auditorium[row][col] ==  'C')
	    	                	totalChildTickets++;
	    	                if (auditorium[row][col] == 'S')
	    	                	totalSeniorTickets++;
	    	            }
	    	            
	    	        }
	    	    	System.out.println("Total Seats: " + totalSeats);
	    	    	System.out.println("Total Tickets: " + totalTickets);
	    	    	System.out.println("Adult Tickets: "  + totalAdultTickets);
	    	    	System.out.println("Child Tickets: " + totalChildTickets);
	    	    	System.out.println("Senior Tickets: " + totalSeniorTickets);
	    	    	double total = (10  * totalAdultTickets) + (5 * totalChildTickets) + (7.50 * totalSeniorTickets );
	    	    	String formattedTotal = String.format("%.2f", total);
	    	    	System.out.println("Total Sales: $" + formattedTotal);
	    	
	    	    }	    	    
	    	    
	    	    public static void writeToFile(char[][] auditorium) {
	    	    	int rows= getMaxRows(auditorium);
	    	    	int cols= getMaxCols(auditorium);
	    	    	
	    	    	 // Specify the name of the output file
	    	    	String filename = "A1.txt";
	    	    	BufferedWriter writer;
					try {
						writer = new BufferedWriter(new FileWriter(filename));
					
	    	    	for (int row = 0; row < rows; row++) {
	    	            for (int col = 0; col < cols; col++) {
	    	            	 // Write the character representing the seat to the file
	    	            	writer.write(auditorium[row][col]);           
	    	            	
	    	            }
	    	         // Write a newline character to separate rows in the file
	    	            writer.newLine();
	    	            
	    	    	 }
	    	    	writer.close();
	    	    	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	
					}
	    	    	
	    	    }

	    	    private static int convertSeatLettertoIndex (char startSeatLetter) {
	    	    	return(startSeatLetter - 'A');
	    	    }
	    	    
	    	    private static char convertIndextoSeatLetter (int index ) {
	    	    	return(char)(index + 'A');
	    	    }
	    	    
	    	    public static void findbestAvailableseat(char[][] auditorium, int rowNum, int numAdult, int numChild, int numSenior, Scanner scanner) {
	    	        int startingSeat = -1;// Initialize the starting seat index for the best available seats
	    	        double col= getMaxCols(auditorium); // Get the maximum number of columns in the auditorium

	    	        int numSeats=  numAdult + numChild + numSenior; // Calculate the total number of seats needed

	    	        double minDistance = col; // Initialize the minimum distance between suggested seats

	    	        double rowMiddle = (col - 1) / 2.0; // Calculate the middle seat index in the row


	    	        for (int i = 0; i < col - numSeats + 1; i++) {
	    	            int j; //pivot
	    	            int currDistance = Math.abs(i + (numSeats - 1) / 2 - (int) rowMiddle);   // Calculate the distance from the middle

	    	            for (j = i; j < i + numSeats; j++) {
	    	                char currSeat = auditorium[rowNum][j];
	    	                if (currSeat != '.') { 
	    	                    break;
	    	                }
	    	            }
	    	            if ((j == numSeats + i) && (currDistance < minDistance)) {
	    	                minDistance = currDistance;
	    	                startingSeat = i;  // Update the starting seat index for the best available seats

	    	            }
	    	        }
	    	   
	    	    if (startingSeat != -1) {
	    	    	if (numSeats==1 )	
	    	    		   // If there is only one seat to reserve, display its location

   	            System.out.println (""+ (rowNum + 1) + convertIndextoSeatLetter(startingSeat) );
	    	    	else
	    	    		// If there are multiple seats to reserve, display a seat range

	    	    		System.out.println (""+ (rowNum + 1) + convertIndextoSeatLetter(startingSeat) + " - " + (rowNum + 1) + convertIndextoSeatLetter(startingSeat + numSeats-1));
	    	    	
   	            System.out.println("Do you want to reserve? Y/N");

                   String choice = scanner.next();

                   if (choice.equals("Y")) {
                   	// If the user confirms, book the available seats

                       bookAvailableSeats(rowNum, convertIndextoSeatLetter(startingSeat), numAdult, numChild, numSenior);
                       return; 
                   }

   	        } 
	    	    else {
   	        	System.out.println("No Seats Available");
   	        }
	    	        
}

	    	    private static int getMaxCols (char[][] auditorium) {
	    	    	int maxCols = auditorium[0].length;
	    	    	for (int i =0; i < auditorium[0].length; i++) {
	    	    		if (auditorium[0][i] == '\u0000') {
	    	    			// If an empty character is encountered, update maxCols and break
	    	    			maxCols = i;
	    	    			break;
	    	    			 
	    	    		}
	    	    	}
	    	    	
	    	    	return maxCols;
	    	    	
	    	    }
	    	    
	    	    private static int getMaxRows(char[][] auditorium) {
	    	        int maxRows = auditorium.length;
	    	        
	    	        for (int i = 0; i < auditorium.length; i++) {
	    	            if (auditorium[i][0] == '\u0000') {
	    	            	 // If an empty character is encountered in the first column, update maxRows and break
	    	                maxRows = i;
	    	                break;
	    	            }
	    	        }
	    	        
	    	        return maxRows;
	    	    }
} 
