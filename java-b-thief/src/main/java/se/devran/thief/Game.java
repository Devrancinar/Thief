package se.devran.thief;

import java.util.Scanner;

public class Game {
    Resident resident = new Resident("Devran", 100, 25);
    Burglar burglar = new Burglar("Thief", 100, 25);

    public void gameStart(){
        welcomeMenu();
        delay();
        visitLivingRoom();

        while(running){

            String directionFromUser = scan.nextLine().toLowerCase();

            switch(directionFromUser){
                case "go to the bedroom" -> {
                    if(currentLocation.equals("living room")){
                        visitBedroom();
                        currentLocation = "bedroom";
                    } else {
                        System.out.println("You can only go back to the living room from here");
                    }
                }
                case "go to the hall" -> {
                    if(currentLocation.equals("living room")){
                        visitHall(resident, burglar);
                        currentLocation = "hall";
                    } else {
                        System.out.println("You can only go back to the living room from here");
                    }
                }
                case "go to the living room" -> {
                    if(!currentLocation.equals("living room")){
                        visitLivingRoom();
                        currentLocation = "living room";
                    } else {
                        System.out.println("You are already in the living room");
                    }
                }
                case "go to the office" -> {
                    if(currentLocation.equals("living room")){
                        visitOffice();
                        currentLocation = "office";
                    } else {
                        System.out.println("You can only go back to the living room from here");
                    }
                }
                case "go to the kitchen" -> {
                    if(currentLocation.equals("living room")){
                        visitKitchen(resident);
                        currentLocation = "kitchen";
                    } else {
                        System.out.println("You can only go back to the living room from here");
                    }
                }
                default -> {
                    System.out.println("Such room dosen't exist");
                }

            }
        }
    }
    public static void welcomeMenu(){
        System.out.println("Welcome to the game...");
        delay();
        System.out.println("You are currently sleeping on the couch");
        delay();
        System.out.println("*** BIG NOISE ***");
        delay();
        System.out.println("What's that sound? Someone is breaking in!\n");
        delay();
        System.out.println("You can choose these rooms to go into: Hall, bedroom, living room, kitchen and office. Where do you want to go?");
    }

    public static void visitKitchen(Resident resident) {
        if (!fryingPanFound) {
            System.out.println("You are in the kitchen and looking for something to protect yourself with");
            delay();
            System.out.println("There is a frying pan on the counter");
            delay();
            System.out.println("Do you want to pick it up? (yes/no)");

            String choice = scan.nextLine().toLowerCase();

            if (choice.equals("yes")) {
                System.out.println("You picked up the frying pan! Your damage has increased");
                delay();
                System.out.println("Whats next?");
                resident.setDamage(resident.getDamage() + 25);  // Öka skadan för Resident
                fryingPanFound = true;  // Stekpannan är nu plockad
            } else {
                System.out.println("You chose not to pick up the frying pan.");
                delay();
                System.out.println("What do you want to do now?");
            }
        } else {
            System.out.println("You are in the kitchen, but the frying pan has already been taken.");
        }
    }

    public static void visitOffice(){


        if (!burglarIsConscious) {
            System.out.println("You entered the office, the burglar is knocked down.");
            delay();
            System.out.println("Do you want to call the police? (yes/no)");

            String choice = scan.nextLine().toLowerCase();

            if (choice.equals("yes")) {
                System.out.println("***CALLING THE POLICE***");
                delay();
                System.out.println("The police are on their way. You are safe, Good job!");
                delay();
                System.out.println("Thank you for playing!");
                running = false;
            } else {
                System.out.println("You chose not to call the police. You can go back to the living room.");
            }
        } else {
            System.out.println("You entered the office, but the burglar is still conscious. You can't call the police yet");
            delay();
            System.out.println("What do you want to do now?");
        }


    }

    public static void visitHall(Resident resident, Burglar burglar){
        System.out.println("You are now in the hall");
        delay();
        System.out.println("You see the thief and a fight starts!");
        startFight(resident, burglar);
    }

    public static void visitBedroom(){
        System.out.println("You are now in the bedroom");
        delay();
        System.out.println("There is not much to do here...");
        delay();
        System.out.println("What do you want to do next?");
    }

    public static void visitLivingRoom(){
        System.out.println("You are now in the living room, where do you want to go?");
    }



    public static void startFight(Resident resident, Burglar burglar) {
        if (!fryingPanFound) {
            System.out.println("You haven't picked up the frying pan yet! The burglar takes advantage and knocks you out.");
            delay();
            System.out.println("GAME OVER");
            running = false;
            return;
        }

        while (resident.getHealth() > 0 && burglar.getHealth() > 0) {
            System.out.println("You attack the burglar!");
            delay();
            burglar.takeHit(resident.getDamage());

            if (burglar.getHealth() <= 0) {
                burglarIsConscious = false;
                System.out.println("You knocked out the burglar! Good job, go to the office now and call the police!");
                burglarIsConscious = false;
                break;
            }

            System.out.println("The burglar attacks you!");
            resident.takeHit(burglar.getDamage());

            if (resident.getHealth() <= 0) {
                System.out.println("The burglar knocked you out. Game over!");
                running = false;
                break;
            }
        }
    }

    private static Scanner scan = new Scanner(System.in);
    private static String currentLocation = "living room";
    private static boolean fryingPanFound = false;
    private static boolean burglarIsConscious = true;
    private static boolean running = true;


    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
