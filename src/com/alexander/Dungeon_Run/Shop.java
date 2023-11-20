package com.alexander.Dungeon_Run;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    List<Weapon> weaponList = new ArrayList<>();

    public void shopForItems(Player player) {

        System.out.println("Welcome to the Shop.");
        System.out.println("Available weapons for purchase:\n" + getWeaponList());

        boolean isShopping = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Players Gold: " + player.getGold());
            System.out.println("""
                    What would you like to purchase?
                     1. Shadowstrike Dagger - 50 Gold
                     2. Eclipse Crossbow - 100 Gold
                     3. Soulreaper Blade - 200 Gold
                     4. Exit shop""");

            try {

                switch (scanner.next()) {
                    case "1" -> {
                        if (checkIfOutOfGold(player)) {
                            isShopping = false;
                        } else if ( player.getGold() - weaponList.get(0).getPrice() <= 0 ) {
                            System.out.println("You do not have enough funds to purchase this item.");
                        } else {
                            System.out.println("You bought 'Shadowstrike Dagger' for " + weaponList.get(0).getPrice() + "Gold");
                            player.setCurrency(player.getGold() - weaponList.get(0).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(0));
                        }

                    }

                    case "2" -> {
                        if (checkIfOutOfGold(player)) {
                            isShopping = false;
                        } else if ( player.getGold() - weaponList.get(1).getPrice() <= 0 ) {
                            System.out.println("You do not have enough funds to purchase this item.");
                        } else {
                            System.out.println("You bought 'Eclipse Crossbow' for " + weaponList.get(1).getPrice() + "Gold");
                            player.setCurrency(player.getGold() - weaponList.get(1).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(1));
                        }
                    }

                    case "3" -> {
                        if (checkIfOutOfGold(player)) {
                            isShopping = false;
                        } else if ( player.getGold() - weaponList.get(2).getPrice() <= 0 ) {
                            System.out.println("You do not have enough Gold to purchase this item.");
                        } else {
                            System.out.println("You bought 'Soulreaper Blade' for " + weaponList.get(2).getPrice() + "Gold");
                            player.setCurrency(player.getGold() - weaponList.get(2).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(2));
                        }
                    }

                    case "4" -> {
                        System.out.println("Exiting shop.");
                        isShopping = false;
                    }

                    default -> System.out.println("Please choose an existing item.");
                }

            } catch (Exception e) {

                scanner.next();     // Ignores whatever the user typed in that wasn't an integer
                System.out.println("Choose only from the numbers available.");
            }


        } while (isShopping);

    }

    public boolean checkIfOutOfGold(Player player) {

        if (player.getGold() <= 0) {
            System.out.println("You don't have enough Gold. Come back later again. Earn Gold by slaying Monsters.");
            return true;
        } else {
            return false;
        }

    }
    public void addWeaponToList() {
        weaponList.add(new Dagger("Shadowstrike Dagger",20,50));
        weaponList.add(new Crossbow("Eclipse Crossbow",40,100));
        weaponList.add(new Sword("Soulreaper Blade",80,200));
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

}

