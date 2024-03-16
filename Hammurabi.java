package hammurabi;               // package declaration
import java.util.Random;         // imports go here
import java.util.Scanner;
public class Hammurabi {
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }

    void playGame() {
        // declare local variables here: grain, population, etc.
        // statements go after the declarations
    }

    //other methods go here
}

