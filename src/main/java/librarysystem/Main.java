/*
Dean Nijenhuis TE23D
Main, detta är filen som körs, som använder andra filer.
 */

package librarysystem;

import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;

import java.nio.file.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); // skapar ny scanner som läser in input i konsolen
        Library library = new Library(); // skapar en instans av biblioteket
        int choice = -999;

        while (choice != 6) {
            mainMenu();
            choice = choice(6, keyboard);

            switch (choice) {
                case 1:
                    fetchMenu();
                    choice = choice(4, keyboard);

                    switch (choice) {
                        case 1:
                            bookFetchMenu();
                            choice = choice(2, keyboard);
                            switch (choice) {
                                case 1:
                                    library.importAllBooks();
        
                                    System.out.println("Alla böcker importerade");
                                    
                                    break;

                                case 2:
                                    
                                    break;
                            
                                default:
                                    break;
                            }


                            break;
                        case 2:
                            magazineFetchMenu();
                            choice = choice(2, keyboard);

                            switch (choice) {
                                case 1:
                                    
                                    break;

                                case 2:
                                    
                                    break;
                            
                                default:
                                    break;
                            }

                            // library.importAllMagazines();

                            // System.out.println("Alla tidningar importerade");

                            break;

                        case 3:

                        switch (choice) {
                            case 1:
                                
                                break;

                            case 2:
                                
                                break;
                        
                            default:
                                break;
                        }

                            break;

                        case 4:

                        switch (choice) {
                            case 1:
                                
                                break;

                            case 2:
                                
                                break;
                        
                            default:
                                break;
                        }

                            break;

                        default:
                            break;
                    }

                case 2:

                    break;

                case 3: // Skriva ut saker
                    printMenu();
                    choice = choice(2, keyboard);

                    switch (choice) {
                        case 1:
                            library.printBooks();
                            break;

                        case 2:
                            library.printMagazines();
                            break;

                        default:
                            break;
                    }

                    break;

                case 4:
                    library.addBook(keyboard);

                    break;

                case 5:
                    library.addMagazine(keyboard);

                    break;

                case 6:

                    break;

                default:
                    break;
            }
            if (choice != 6) {
                backToMenu(keyboard);
            }
        }

    }

    // Meny metoderna är här för att detblir mycket enklare att läsa och ändra i
    // koden när dem inte är där uppe

    public static void bookFetchMenu() { // hämta böcker på olika sätt
        System.out.println("=============================");
        System.out.println("| Meny:                     |");
        System.out.println("| Val 1: hämta alla böcker. |");
        System.out.println("| Val 2: hämta specifik bok.|");
        System.out.println("| Skriv 1-2.                |");
        System.out.println("=============================");
    }

    public static void magazineFetchMenu() { //hämta tidningar på olika sätt
        System.out.println("=================================");
        System.out.println("| Meny:                         |");
        System.out.println("| Val 1: hämta alla tidningar.  |");
        System.out.println("| Val 2: hämta specifik tidning.|");
        System.out.println("| Skriv 1-2.                    |");
        System.out.println("=================================");
    }

    public static void fetchMenu() { // menyn när man ska hämta olika saker från server
        System.out.println("===============================");
        System.out.println("| Meny:                       |");
        System.out.println("| Val 1: hämta böcker.        |");
        System.out.println("| Val 2: hämta tidningar.     |");
        System.out.println("| val 3: hämta användare.     |");
        System.out.println("| val 4: hämta avstängda.     |");
        System.out.println("| Skriv 1-3.                  |");
        System.out.println("===============================");
    }

    public static void printMenu() { // meny för när man vill skriva ut
        System.out.println("==================================");
        System.out.println("| Meny:                          |");
        System.out.println("| Val 1: skriv ut alla böcker.   |");
        System.out.println("| Val 2: skriv ut alla tidningar.|");
        System.out.println("| Skriv 1-2.                     |");
        System.out.println("==================================");
    }

    public static void mainMenu() { // main menyn
        System.out.println("==========================================");
        System.out.println("| Meny:                                  |");
        System.out.println("| Val 1: Hämta från servern.             |");
        System.out.println("| Val 2: Hämta tidningar.     asdasd     |");
        System.out.println("| Val 3: Skriv ut hämtade artiklar.      |");
        System.out.println("| Val 4: Lägg till en bok i systemet.    |");
        System.out.println("| Val 5: Lägg till en tidning i systemet.|");
        System.out.println("| Val 6: Avsluta programmet.             |");
        System.out.println("| Skriv 1-6.                             |");
        System.out.println("==========================================");
    }

    public static String readString(Scanner keyboard) { // gör som namnet heter
        String temp = "sfjsifj"; // blev ibland problem när stringen var nullad

        temp = keyboard.next();
        return temp;
    }

    public static int readInt(Scanner keyboard) { // gör som namnet heter
        int temp = 0;
        boolean success = false;
        while (!success) {
            try {
                temp = keyboard.nextInt();
                success = true;
            } catch (Exception e) {
                System.out.println("Det får endast vara en int!");
                success = false;
                keyboard.nextLine();
            }
        }
        keyboard.nextLine();
        return temp;

    }

    public static int choice(int numberChoices, Scanner keyboard) { // dyanamisk val metod där det
        // går att besämma antalet val;
        boolean success = false;
        int choice = 0;
        while (!success) {
            choice = readInt(keyboard);
            if (choice > 0 && choice <= numberChoices) {
                success = true;
            } else {
                System.out.println("ange ett tal mellan 1 och " + numberChoices);
            }
        }
        try {
            Thread.sleep(1000);

        } catch (Exception e) {
            // kommer ej bli fel
        }

        return choice;
    }

    public static void backToMenu(Scanner keyboard) { // gör så att man ska trycka enter för att gå tillbaka till meny
                                                      // (endast upplevelse)
        System.out.println("tryck enter för att återgå till meny");
        keyboard.nextLine();
    }
}