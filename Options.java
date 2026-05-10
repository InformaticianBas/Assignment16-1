import java.util.Scanner;

public class Options {
    // Used to close program if the help option is used
    public static boolean HelpOption = false;

    // Parse arguments
    public static void parse(String[] args, Scanner input) {
        if (args.length == 0) {
            return;
        }
        for (String arg: args) {
                if (arg.equals("-l")) {
                    VaporStore.userLogin(input);
                } else if (arg.equals("-h")) {
                    HelpOption = true;
                    printHelp();
                } else {
                    printOptionError();
                }
            
        }
    }

    public static void printOptionError() {
        System.out.println("Unable to process option. Use -h for help.");
    }

    public static void printHelp() {
        System.out.println("Usuage: java VaporStore [option] [file]");
    }

}
