package hammurabi;               // package declaration
import java.sql.SQLOutput;
import java.util.InputMismatchException;
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
    Random rand = new Random();  // instance field variables
    Scanner scanner = new Scanner(System.in);
    private int population = 100;
    private int grainInStorage = 2800;
    private int acresOwned = 1000;
    private int landValue = 19;

    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }

    void playGame() {
        // need a SOUT string statement here >>>>> System.out.println("WELCOME TO DANNY'S GAME")
        // ROUGH DRAFT BACKBONE
        //start game prompt: creating game loop for each of the 10 years
        for (int year = 1; year <= 10; year++) {
            printSummary(year);
            int acresToBuy = askHowManyAcresToBuy(landValue, grainInStorage);
            if (acresToBuy > 0) {
                acresOwned += acresToBuy;
                grainInStorage -= acresToBuy * landValue;
            } else {
                int acresToSell = askHowManyAcresToSell(acresOwned);
                acresOwned -= acresToSell;
            }
            int grainToFeed = askHowMuchGrainToFeedPeople(grainInStorage);
            grainInStorage -= grainToFeed;

            int acresToPlant = askHowManyAcresToPlant(acresOwned, population, grainInStorage);
            grainInStorage -= acresToPlant * 2; // = 2 bushels per acre to plant

            // yearly outcomes
            int plagueDeaths = plagueDeaths(population);
            population -= plagueDeaths;

            int starvationDeaths = starvationDeaths(population, grainToFeed);
            population -= starvationDeaths;
            if (uprising(population, starvationDeaths)) {
                System.out.println("YOU'VE BEEN KICKED OUT OF OFFICE!");
                break;
            }

            int immigrants = immigrants(population, acresOwned, grainInStorage);
            population += immigrants;

            int harvestAmount = harvest(acresToPlant);
            grainInStorage += harvestAmount;

            int eatenByRats = grainEatenByRats(grainInStorage);
            grainInStorage -= eatenByRats;

            landValue = newCostOfLand();

            if (year == 10) {
                finalSummary();
                break;
            }
        }
    }

    int askHowManyAcresToBuy(int price, int bushels) {
        System.out.println("Land is currently worth " + price + " bushels per acre. ");
        int acresToBuy = getNumber("How many acres will you buy? ");
        while (acresToBuy * price > bushels) {
            System.out.println("0 great Hammurabi, we have only " + bushels + " bushels of grain!");
            acresToBuy = getNumber("How many acres will buy? ");
        }
        return acresToBuy;
    }

    int askHowManyAcresToSell(int acresOwned) {
        int acresToSell = getNumber("How many acres will you sell? ");
        while (acresToSell > acresOwned) {
            System.out.println("O great Hammurabi, we cannot sell more than we own!");
            acresToSell = getNumber("How many acres will you sell? ");
        }
        return acresToSell;
    }

    int askHowMuchGrainToFeedPeople(int bushels) {
        int grainToFeed = getNumber("How many bushels of grain will you feed the people? ");
        while (grainToFeed > bushels) {
            System.out.println("O great Hammurabi, we have only " + bushels + " bushels of grain!");
            grainToFeed = getNumber("How many bushels of grain will you feed the people? ");
        }
        return grainToFeed;
    }
    
    int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        int acresToPlant = getNumber("How many acres will you plant with seed? ");
        while(acresToPlant > acresOwned || acresToPlant / 2 > bushels || acresToPlant > population * 10) {
            if (acresToPlant > acresOwned) {
                System.out.println("O great Hammurabi, we have only " + acresOwned + " acres of land!");
            } else if (acresToPlant / 2 > bushels) {
                System.out.println("O great Hammurabi, we have only " + bushels + " bushels of grain for planting!");
            } else if (acresToPlant > population * 10); {
                System.out.println("O great Hammurabi, we don't have enough people to plant that much land!");
            }
            acresToPlant = getNumber("How many acres will you plant with seed? ");
        }
        return acresToPlant;
    }


    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            } // the loop will keep going and continue until a valid number is entered
        }
    }


    void printSummary( int year){
        System.out.println("0 great Hammurabi!");
        System.out.println("You are in year " + year + " of your ten year rule.");
        System.out.println("The population is now " + population + ".");
        System.out.println("We harvested " + 'z' + " bushels at " + 'w' + " bushels per acre.");
        // placeholder z for total and w for bushels per acre , need to come back and finish code
        System.out.println("Rats destroyed " + 'r' + " bushels, leaving " + grainInStorage + " bushels in the stoarge.");
        // placeholder r for grain eaten by rats, need to come back and finish code
        System.out.println("The city owns " + acresOwned + " acres of land.");
        System.out.println("Land is currently worth " + landValue + " bushels per acre.");
    }


    void finalSummary() {
        System.out.println("You've completed your 10 year term!!!");
        System.out.println("Population: " + population);
        System.out.println("Acres owned: " + acresOwned);
        System.out.println("Bushels in storage: " + grainInStorage);
        System.out.println("Land value: " + landValue + " bushels per acre");
    }


    int plagueDeaths(int population) {
    }

    int starvationDeaths(int population, int bushelsFedToPeople) {
    }

    boolean uprising(int population, int howManyPeopleStarved) {
    }

    int immigrants(int population, int acresOwned, int grainInStorage) {
    }

    int harvest(int acres, int bushelsUsedAsSeed) {
    }

    int grainEatenByRats(int bushels) {
    }

    int newCostOfLand() {
    }
}
