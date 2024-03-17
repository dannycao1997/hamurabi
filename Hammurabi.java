package hammurabi;               // package declaration
import java.util.InputMismatchException;
import java.util.Random;         // imports go here
import java.util.Scanner;

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
        System.out.println("Welcome to Danny's Hammurabi Game!");


        System.out.println(" ██░ ██  ▄▄▄       ███▄ ▄███▓ ███▄ ▄███▓ █    ██  ██▀███   ▄▄▄       ▄▄▄▄    ██▓\n" +
                "▓██░ ██▒▒████▄    ▓██▒▀█▀ ██▒▓██▒▀█▀ ██▒ ██  ▓██▒▓██ ▒ ██▒▒████▄    ▓█████▄ ▓██▒\n" +
                "▒██▀▀██░▒██  ▀█▄  ▓██    ▓██░▓██    ▓██░▓██  ▒██░▓██ ░▄█ ▒▒██  ▀█▄  ▒██▒ ▄██▒██▒\n" +
                "░▓█ ░██ ░██▄▄▄▄██ ▒██    ▒██ ▒██    ▒██ ▓▓█  ░██░▒██▀▀█▄  ░██▄▄▄▄██ ▒██░█▀  ░██░\n" +
                "░▓█▒░██▓ ▓█   ▓██▒▒██▒   ░██▒▒██▒   ░██▒▒▒█████▓ ░██▓ ▒██▒ ▓█   ▓██▒░▓█  ▀█▓░██░\n" +
                " ▒ ░░▒░▒ ▒▒   ▓▒█░░ ▒░   ░  ░░ ▒░   ░  ░░▒▓▒ ▒ ▒ ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░░▒▓███▀▒░▓  \n" +
                " ▒ ░▒░ ░  ▒   ▒▒ ░░  ░      ░░  ░      ░░░▒░ ░ ░   ░▒ ░ ▒░  ▒   ▒▒ ░▒░▒   ░  ▒ ░\n" +
                " ░  ░░ ░  ░   ▒   ░      ░   ░      ░    ░░░ ░ ░   ░░   ░   ░   ▒    ░    ░  ▒ ░\n" +
                " ░  ░  ░      ░  ░       ░          ░      ░        ░           ░  ░ ░       ░  \n" +
                "                                                                          ░     ");



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
                System.out.println("TOO MANY PEOPLE STARVED, YOU'VE BEEN KICKED OUT OF OFFICE!");
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

    // loop to get response
    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }


    void printSummary(int year){
        System.out.println("Hammurabi: I beg to report to you,");
        System.out.println("You are in year " + year + " of your ten year rule.");
        System.out.println("The population is now " + population + ".");
        System.out.println("Rats destroyed the bushels, leaving " + grainInStorage + " bushels in the storage.");
        System.out.println("The city owns " + acresOwned + " acres of land.");
    }


    void finalSummary() {
        System.out.println("GREAT JOB! You've completed your 10 year term!!");
        System.out.println("Population: " + population);
        System.out.println("Acres owned: " + acresOwned);
        System.out.println("Bushels in storage: " + grainInStorage);
        System.out.println("Land value: " + landValue + " bushels per acre.");
    }


    // code to calculate yearly outcomes
    int plagueDeaths(int population) {
        return rand.nextInt(100) < 15 ? population / 2 : 0;
    }

    int starvationDeaths(int population, int grainFedToPeople) {
        int peopleFed = grainFedToPeople / 20;
        return population > peopleFed ? population - peopleFed : 0;
    }

    boolean uprising(int population, int howManyPeopleStarved) {
        return howManyPeopleStarved > (population * 0.45);
    }

    int immigrants(int population, int acresOwned, int grainInStorage) {
        if (population > 0){
            return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
        }
        return 0;
    }

    int harvest(int acresPlanted) {
        int yield = rand.nextInt(6) + 1; //  yields 1-6 bushels harvest per acre
        return acresPlanted * yield;
    }

    int grainEatenByRats(int grainInStorage) {
        if (rand.nextInt(100) < 40) { // 40 % chance
            int percent = rand.nextInt(21 ) + 10; // 10 - 30 % chance
            return grainInStorage * percent / 100;
        }
        return 0;
    }

    int newCostOfLand() {
        return rand.nextInt(7) + 17;
    }   // random value: 17-23 bushels per acre

}
