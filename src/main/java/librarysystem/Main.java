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
        Scanner keyboard = new Scanner(System.in);  //skapar ny scanner som läser in input i konsolen
        Library library = new Library();              //skapar en instans av biblioteket
        int val = -999;

        while (val != 6) {
            mainMenu();
            val(6,keyboard);
           

            switch (val) {
                case 1:
                    library.importAllBooks();

                    System.out.println("Alla böcker importerade");

                    break;

                case 2:
                    library.importAllMagazines();

                    System.out.println("Alla tidningar importerade");

                    break;

                case 3: // Skriva ut saker
                    printMenu();
                    val(2,keyboard);
                    
                    switch (val) {
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
            if (val != 6) {
                backToMenu(keyboard);
            }
        }

    }

    // Meny metoderna är här för att detblir mycket enklare att läsa och ändra i
    // koden när dem inte är där uppe

    public static void printMenu() {  //meny för när man vill skriva ut
        System.out.println("==================================");
        System.out.println("| Meny:                          |");
        System.out.println("| Val 1: skriv ut alla böcker.   |");
        System.out.println("| Val 2: skriv ut alla tidningar.|");
        System.out.println("==================================");
    }

    public static void mainMenu() { //main menyn
        System.out.println("==========================================");
        System.out.println("| Meny:                                  |");
        System.out.println("| Val 1: Hämta böcker.                   |");
        System.out.println("| Val 2: Hämta tidningar.                |");
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

    public static int val(int antalVal, Scanner keyboard){ // dyanamisk val metod där det
        // går att besämma antalet val;
        boolean success = false;
        int val = 0;
        while (!success) {
            val = readInt(keyboard);
            if (val > 0 && val <= antalVal) {
                success = true;
            } else {
                System.out.println("ange ett tal mellan 1 och " + antalVal);
            }
        }
        try {
            Thread.sleep(1000);
            
        } catch (Exception e) {
            // kommer ej bli fel
        }

        return val;
    }

    public static void backToMenu(Scanner keyboard) { // gör så att man ska trycka enter för att gå tillbaka till meny
                                                      // (endast upplevelse)
        System.out.println("tryck enter för att återgå till meny");
        keyboard.nextLine();
    }
}