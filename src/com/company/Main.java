package com.company;

import java.util.Scanner;

public class Main {

    public Scanner keybord = new Scanner(System.in);

    public void buildRooms() {
        Room room1 = new Room("1","derp");
        Room room2 = new Room("2","derp");
        Room room3 = new Room("3","derp");
        Room room4 = new Room("4","derp");
        Room room5 = new Room("5","derp");
        Room room6 = new Room("6","derp");
        Room room7 = new Room("7","derp");
        Room room8 = new Room("8","derp");
        Room room9 = new Room("9","derp");


        room1.setEast(room2);

    }

    public void MainMenu () {

        buildRooms();

        boolean loop = true;
        while (loop) {

        String fullAnswer = keybord.nextLine();
//        int firstIndex = fullAnswer.indexOf(' ');
//        String firstPartAnswer = fullAnswer.substring(0,firstIndex);
//        String secondPartAnswer = fullAnswer.substring(firstIndex);

        switch (fullAnswer) {

            case ("help") -> System.out.println("helping");
            case ("exit") -> System.out.println("Exit");
            case ("look") -> System.out.println("looking");
            case ("go north") -> System.out.println("go north");
            case ("go south") -> System.out.println("go south");
            case ("go east") -> System.out.println("go east");
            case ("go west") -> System.out.println("go west");

        }


        }

    }

    public static void main(String[] args) {
	// write your code here
        Main action = new Main();

        new Main().MainMenu();


    }
}
