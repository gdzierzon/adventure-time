package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventureTime
{
    static ArrayList<StepClass> adventureSteps;
    static Scanner scanner = new Scanner(System.in);

    static void main()
    {
        adventureSteps = loadAdventure();

        // start the application
        homeScreen();
    }

    public static void homeScreen()
    {
        System.out.println("Welcome to Adventure Time!");
        System.out.println("--------------------------");
        System.out.println();
        System.out.println("(P)lay");
        System.out.println("(Q)uit");
        System.out.print("Make a selection: ");

        String choice = scanner.nextLine().toUpperCase().strip();

        switch (choice)
        {
            case "P":
                gameScreen(1);
                break;
            case "Q":
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                homeScreen();
        }

    }

    public static  void gameScreen(int id)
    {

        for (int i = 0; i < adventureSteps.size(); i++)
        {
            StepClass step = adventureSteps.get(i);
            if(step.getId() == id)
            {
                System.out.println();
                System.out.println(step.getStoryText());
                System.out.println();
                System.out.println("1) " + step.getOption1Text());
                System.out.println("2) " + step.getOption2Text());
                System.out.print("Make a selection: ");

                String choice = scanner.nextLine().strip();

                switch (choice)
                {
                    case "1":
                        System.out.println("Option 1 selected");
                        break;
                    case "2":
                        System.out.println("Option 2 selected");
                        break;
                    default:
                        System.out.println("Select a valid option - try again");
                }
            }
        }
    }

    public static ArrayList<StepClass> loadAdventure()
    {
        // create the container
        // ArrayLists grow as needed when you add new items
        ArrayList<StepClass> steps = new ArrayList<>();

        // populate the container
        try {
            FileReader fileReader = new FileReader("adventure1.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null)
            {
                String[] columns = line.split("\\|");

                int id = Integer.parseInt(columns[0]);
                String text = columns[1];
                String option1Text = columns[2];
                int option1NextId = Integer.parseInt(columns[3]);
                String option2Text = columns[4];
                int option2NextId = Integer.parseInt(columns[5]);

                // create a Step from the data in the current line
                StepClass stepClass = new StepClass(id, text, option1Text, option1NextId, option2Text, option2NextId);

                // add the current step to the container (ArrayList)
                steps.add(stepClass);


                // read the next line
                line = bufferedReader.readLine();
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }

        // return the container
        return steps;
    }
}
