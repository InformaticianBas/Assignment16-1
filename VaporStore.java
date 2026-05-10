import java.util.Scanner;

public class VaporStore {
    public static void main(String[] args) {
        // Creates a scannder object called input to grab user entries throughout the program
        Scanner input = new Scanner(System.in);
        
         // Handles command-line arguments in Options
        Options.parse(args, input);
         if (Options.HelpOption == true) {
            return;
        }

        // Handles initializing Database for program
        Database.initDB();
        
        boolean active = true;
        System.out.println("Welcome to the TUI for the Vaper Video Game Store! \n");
        System.out.println("This is the main menu. \n Press 'l' to login as an existing user or 'c' to create a new user. \n Press 'd' to list the catalog of  games. When not logged in, it will not display those that are \"M for Mature\". \n Press 'x' to exit the program. ");

        // Main Menu Sequence. Starts with infinite loop.
        while (active) {
            char MainMenuRawInput = input.nextLine().charAt(0);
            char MainMenuSelection = Character.toLowerCase(MainMenuRawInput);
            if (MainMenuSelection == 'x') {
                System.out.println("See you next time!");
                break;
            }
            else
                switch (MainMenuSelection) {
                    case 'c':
                        createUser(input);
                        break;
                    case 'l':
                        userLogin(input);
                        break;
                    case 'd':
                        printCatalog();
                        break;
                    default:
                        printAppError();
                        break;
            }
        }
        input.close(); 
    }



    public static void createUser(Scanner input){
        // Grabs email, gamertag, and password
        System.out.println("New Email: ");
        String email = input.nextLine();
        System.out.println("Gamer Tag: ");
        String gamertag = input.nextLine();
        System.out.println("Password: ");
        String pass = input.nextLine();

        // Function passes the string variables in this function to a function that adds in entry to Accounts.
        // It does however leave the date field null. Due to complexity and scope that wasn't added.
        Database.createAccount(email, gamertag, pass);
    }

       public static void userLogin(Scanner input){
        // Grabs email and password
        System.out.println("Enter Email: ");
        String email = input.nextLine();
        System.out.println("Enter Password: ");
        String password = input.nextLine();

        // Passes the variables to a function that validates them and returns a boolean.
        if (Database.validateLogin(email, password)) {
            System.out.println("Login successful. Welcome back, " + email + "!");
        } else {
            System.out.println("Login failed. Proceeding as guest.");
        }
    }

    public static void printCatalog(){
        Database.showCatalog();
    }

    // For when user enters a value that isn't an option.
    public static void printAppError(){
        System.out.println("Error. Invalid Input. Please try again.");
    }
}
