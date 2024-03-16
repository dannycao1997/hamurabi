package hammurabi;               // package declaration
import java.sql.SQLOutput;
import java.util.Random;         // imports go here
import java.util.Scanner;

/* USING AS EXAMPLE
O great Hammurabi!
You are in year 1 of your ten-year rule.
In the previous year 0 people starved to death.
In the previous year 5 people entered the kingdom.
The population is now 100.
We harvested 3000 bushels at 3 bushels per acre.
Rats destroyed 200 bushels, leaving 2800 bushels in storage.
The city owns 1000 acres of land.
Land is currently worth 19 bushels per acre.
*/



public class Hammurabi {
    Random rand = new Random();  // instance field variable
    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }


    void playGame() {
        // declare local variables here: grain, population, etc.
        // statements go after the declarations


        //initializing conditions for starting game
        int year = 1;
        int population = 100;
        int grainInStorage = 2800;
        int acresOwned = 1000;
        int landValue = 19;


        //starting game prompt: creating game loop for each of the 10 years
        for(int year = 1; year <= 10; year++) {


            // ROUGH DRAFT BACK BONE

        }// methods
        int askHowManyAcresToBuy(int price, int bushels){

        }

        int askHowManyAcresToSell(int acresOwned){

        }

        int askHowMuchGrainToFeedPeople(int bushels){

        }

        int askHowManyAcresToPlant(int acresOwned, int population, int bushels){

        }

        int plagueDeaths(int population){

        }

        int starvationDeaths(int population, int bushelsFedToPeople){

        }

        boolean uprising(int population, int howManyPeopleStarved){

        }

        int immigrants(int population, int acresOwned, int grainInStorage){

        }

        int harvest(int acres, int bushelsUsedAsSeed){

        }

        int grainEatenByRats(int bushels){

        }

        int newCostOfLand(){

        }















    }


    }

    //Saving this code for later
            /*System.out.println("0 great Hammurabi!");
            System.out.println("You are in year " + year + " of your ten year rule.");
            System.out.println("In the previous year " + year + " people starved to death.");
            System.out.println("The population is now " + population + ".");
            System.out.println("Land is currenty worth " + landValue + " bushels per acre.");*/

