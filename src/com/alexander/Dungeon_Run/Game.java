package com.alexander.Dungeon_Run;
import java.util.Scanner;
import static com.alexander.Dungeon_Run.Monster.newMonsterEncounter;

public class Game {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();
    WriteScoreFile playerStats = new WriteScoreFile();
    MonsterEncounter monsterEncounter = new MonsterEncounter();
    Shop shop = new Shop();

    public void mainMenu() {
        player.setPlayerName(scanner.next());

        long start1 = System.currentTimeMillis();
        long end1 = start1 + 15 * 100;
        while (System.currentTimeMillis() < end1) {
        }

        System.out.println("\n" + player.getPlayerName() + "? So be it! Let's begin the adventure.");
        System.out.println("The sun sets.. you delve deeper into the unknown..");

        long start2 = System.currentTimeMillis();
        long end2 = start2 + 35 * 100;
        while (System.currentTimeMillis() < end2) {
        }

        shop.addWeaponToList(); // Beginning the game by adding weapons to the shop
        boolean continueGame;

        do {
            continueGame = true;

            System.out.println("\nCurrent player: " + player.getPlayerName() +
                    "\nWhat would you like to do next? " +
                    "\n 1. Enter Dungeon" +
                    "\n 2. Check Your Status" +
                    "\n 3. Visit Weapon Shop" +
                    "\n 4. Exit The Game");

            System.out.print("Please Enter Your choice: ");

            try {
                switch (scanner.nextInt()) {
                    case 1 -> fightMenu();
                    case 2 -> System.out.println("\n" + player.playerStats());
                    case 3 -> shop.shopForItems(player);
                    case 4 -> {System.out.println("Exiting Game");
                        if (player.currentWeapon.size() == 1) {
                            playerStats.writeScoreFile(); // Creates and/or writes players final scores in a text file
                        }
                        continueGame = false;
                    }
                    default -> System.out.println("Please choose an action that's available.");
                }
            } catch (Exception e) {
                scanner.next();
                System.out.println("Invalid choice! Please try again and enter a number between 1 and 4.");
            }
        } while (continueGame);
        System.out.println("Thanks for playing! Goodbye!");
    }

    private void fightMenu() {

        System.out.println();
        System.out.println("************************************************************************");
        System.out.println("You encountered different monsters in the Dungeon!");
        System.out.println("************************************************************************");
        System.out.println("\n" + """
                Which Monster would you like to fight? Type in an option from below
                
                ************************************************************************
                1. Goblin Grunt - Low Level Monster - Health 20
                
                2. Flameforge Fiend - Medium Level Monster - Health 35
                
                3. Venomous Vortex Serpent - High Level Monster - Health 70
                
                4. Thunderclaw Behemoth - Boss Level Monster - Health 300
                ************************************************************************
                """);

        try {

            int chosenMonster = scanner.nextInt();
            System.out.println("\nYou chose Monster type: " + chosenMonster);

            monsterEncounter.monsterBattle(player, newMonsterEncounter(chosenMonster));

            if (player.isAlive()) {
                player.setHealth(player.getMaxHealth());
                player.setExperience(player.getExperience() + 10);
                player.checkIfLevelUp();
                player.setCurrency(player.getGold() + 15);
            }

        } catch (Exception e) {

            scanner.next();
            System.out.println("Invalid choice! Please try again and enter a number between 1 and 4.");
        }
    }
}
