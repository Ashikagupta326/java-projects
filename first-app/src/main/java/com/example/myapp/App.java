package com.example.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A small console tool for planning and reviewing study sessions.
 */
public class App {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final List<String> sessions = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("       STUDY SESSION PLANNER");
        System.out.println("================================");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add a study goal");
            System.out.println("2. View today's goals");
            System.out.println("3. Remove a goal");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = INPUT.nextLine().trim();

            switch (choice) {
                case "1" -> addGoal();
                case "2" -> showGoals();
                case "3" -> removeGoal();
                case "4" -> running = false;
                default -> System.out.println("Please choose 1, 2, 3, or 4.");
            }
        }
        System.out.println("Good luck with your studies!");
    }

    private static void addGoal() {
        System.out.print("Enter subject or task: ");
        String task = INPUT.nextLine().trim();
        if (task.isEmpty()) {
            System.out.println("A goal cannot be empty.");
            return;
        }
        System.out.print("Planned duration in minutes: ");
        try {
            int minutes = Integer.parseInt(INPUT.nextLine().trim());
            if (minutes <= 0) {
                System.out.println("Duration must be greater than zero.");
                return;
            }
            sessions.add(task + " (" + minutes + " minutes)");
            System.out.println("Goal added to your plan.");
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a whole number for the duration.");
        }
    }

    private static void showGoals() {
        if (sessions.isEmpty()) {
            System.out.println("No goals added yet.");
            return;
        }
        System.out.println("\n--- YOUR STUDY PLAN ---");
        for (int i = 0; i < sessions.size(); i++) {
            System.out.println((i + 1) + ". " + sessions.get(i));
        }
    }

    private static void removeGoal() {
        showGoals();
        if (sessions.isEmpty()) return;
        System.out.print("Enter the goal number to remove: ");
        try {
            int index = Integer.parseInt(INPUT.nextLine().trim()) - 1;
            if (index < 0 || index >= sessions.size()) {
                System.out.println("That goal number does not exist.");
                return;
            }
            System.out.println("Removed: " + sessions.remove(index));
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid goal number.");
        }
    }
}
