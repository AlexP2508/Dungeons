package com.alexander.Dungeon_Run;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.alexander.Dungeon_Run.Colors.*;

public class Player implements ICombat{


    // Instantiation
    Random random = new Random();

    // Player stats
    private String playerName;
    private int strength = 5;       //
    private int intelligence = 5;   // str, int and agi increases with 2 every level
    private int agility = 5;        //
    private int health = 50;
    private int maxHealth = 50;
    private int level = 0;
    private int minDamage = 5;
    private int maxDamage = 10;
    private int currency = 0;
    private int experience = 0;
    private int maxExperience = 100;
    private int monstersKilled = 0;

    List<Weapon> currentWeapon = new ArrayList<>();      // Keeps track of what weapon the player currently has in possession

    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void checkIfLevelUp() {

        if (experience >= maxExperience) {
            System.out.println("\n  LEVEL UP.\nAll stats have increased.");

            level += 1;
            experience = 0;
            maxExperience += 15;            // Increasing the maxExp makes it harder to level up
            maxHealth += 10;
            health = maxHealth;
            strength += 2;
            intelligence += 2;
            agility += 2;
            minDamage += 1;
            maxDamage += 1;
        }
    }

    @Override
    public int fight() {
        return calculateDamage();
    }

    @Override
    public int calculateDamage() {

        int damageDone = 0;
        int noWeaponDamage = ((random.nextInt(minDamage, maxDamage)) + strength);

        if (currentWeapon.size() == 0) {

            if ( intelligence >= random.nextInt(1,20) ) {
                System.out.println("Your intellect found a weak spot " +
                        "and so you were able to double the damage to the monster");
                damageDone += noWeaponDamage * 2;
            }else {
                damageDone += noWeaponDamage;
            }
        } else {
            // Here shall it say players current weapon, which will return the new damage made to monster
            if ( intelligence >= random.nextInt(1,20) ) {
                System.out.println("Your intellect found a weak spot " +
                        "and so you were able to double the damage to the monster");
                damageDone += ((noWeaponDamage + currentWeapon.get(0).getDamage()) * 2);
            }else {
                damageDone += noWeaponDamage + currentWeapon.get(0).getDamage();
            }

        }

        return damageDone;
    }

    public void dodged(Monster monster) {

        if (playerDidDodge()) {
            System.out.println("You dodged the monsters attack.");
        } else {
            int damageTaken = monster.fight();

            System.out.println("You took " + damageTaken + " damage.");
            health -= damageTaken;      // takes damage from monster

            if (health <= 0) {
                System.out.println("You died while fighting " + monster.getName());
                System.out.println("GAME OVER");
                System.exit(0);
            }
        }

    }

    public boolean playerDidDodge() {

        boolean didDodge;
        int dodge = random.nextInt(1,50);

        didDodge = dodge >= 1 && dodge <= getAgility();

        return didDodge;
    }

    public boolean playerDidFlee() {
        int chanceToRun = this.agility;
        Random random = new Random();
        int randomNum = random.nextInt(10) + 1;

        if (randomNum <= chanceToRun) {
            System.out.println("\nYOU FLED FROM THE MONSTER!");
            return true;
        } else {
            System.out.println("\nYOU FAILED TO FLEE FROM THE MONSTER!");
            return false;
        }

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAgility() {
        return agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getGold() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        checkIfLevelUp();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public int getMonstersKilled() {
        return monstersKilled;
    }

    public void setMonstersKilled(int monstersKilled) {
        this.monstersKilled = monstersKilled;
    }

    public List<Weapon> getCurrentWeapon() {
        return currentWeapon;
    }

    public String playerStats() {
        return
                RESET + GREEN  + "Player Name: " + playerName + "," +
                RESET + BLUE + " Strength = " + strength + "," +
                RESET + YELLOW + " Intelligence = " + intelligence + "," +
                RESET + CYAN + " Agility = " + agility + "," +
                RESET + PURPLE + " Health = " + health + "," +
                RESET + GREEN + " Level = " + level + "," +
                RESET + BLUE + " MinDamage = " + minDamage + "," +
                RESET + YELLOW + " MaxDamage = " + maxDamage + "," +
                RESET + CYAN + " Currency = " + currency + "," +
                RESET + PURPLE + " Experience = " + experience +
                RESET;
    }






}
