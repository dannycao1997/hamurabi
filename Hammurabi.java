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
        // need a SOUT string statement here >>>>>
        // ROUGH DRAFT BACK BONE
        //starting game prompt: creating game loop for each of the 10 years
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
            grainInStorage -= acresToPlant * 2; // 2 buhles per acre to plant

            // following year

            int plagueDeaths = plagueDeaths(population);
            population -= plagueDeaths;

            int starvationDeaths = starvationDeaths(population, grainToFeed);
            population -= starvationDeaths;
            if(uprising(population, starvationDeaths)) {
                System.out.println("YOU'VE BEEN KICKED OUT OF OFFICE!");
                break;
            }

            int immigrants = immigrants(population, acresOwned, grainInStorage);
            population += immigrants;

            // rough draft update 







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

        int newCostOfLand() {
        }


        void printSummary(int year){
            System.out.println("0 great Hammurabi!");
            System.out.println("You are in year " + year + " of your ten year rule.");
            System.out.println("In the previous year " + year + " people starved to death.");
            System.out.println("The population is now " + population + ".");
            System.out.println("Land is currenty worth " + landValue + " bushels per acre.");
        }
    }
