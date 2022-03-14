package com.company;

import java.util.Scanner;

public class Main {

    public Scanner keybord = new Scanner(System.in);

    public void MainMenu () {

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
        new Main().MainMenu();



    }
}
