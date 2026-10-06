/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package treasure;

import java.util.Scanner;

/**
 *
 * @author H.P
 */
public class Ass1320220837 {

    public static void main(String[] args) {
        Bag bag = new Bag();
  Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Treasure Box Menu ---");
            System.out.println("1. Add treasure");
            System.out.println("2. Remove treasure");
            System.out.println("3. Show all treasures");
            System.out.println("4. Check treasure");
            System.out.println("5. Most frequent treasure");
            System.out.println("6. Generate random treasures");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // لتفريغ السطر بعد قراءة الرقم

            switch (choice) {
                case 1:
                    System.out.print("Enter treasure name to add: ");
                    String addName = sc.nextLine();
                    bag.addTreasure(addName);
                    break;
                case 2:
                    System.out.print("Enter treasure name to remove: ");
                    String removeName = sc.nextLine();
                    bag.removeTreasure(removeName);
                    break;
                case 3:
                    System.out.println("\nAll treasures in the box:");
                    bag.countTreasures();
                    break;
                case 4:
                    System.out.print("Enter treasure name to check: ");
                    String checkName = sc.nextLine();
                    if (bag.containsTreasure(checkName)) {
                        System.out.println(checkName + " is in the box.");
                    } else {
                        System.out.println(checkName + " is not in the box.");
                    }
                    break;
                case 5:
                    System.out.println("Most frequent treasure:");
                    System.out.println(bag.mostFrequentTreasure());
                    break;
                case 6:
                    bag.generateRandomTreasures();
                    System.out.println("Random treasures added!");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
