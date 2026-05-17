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
        String baseUrl = "http://localhost:3000/";
        Gson gson = new Gson();
        menu();
        
    }

    public static void menu() {
        System.out.println("==========================================");
        System.out.println("| Meny:                                  |");
        System.out.println("| Val 1: Hämta böcker.                   |");
        System.out.println("| Val 2: Hämta tidningar.                |");
        System.out.println("| Val 3: Skriv ut hämtade artiklar.      |");
        System.out.println("| Val 4: Lägg till en bok i systemet.    |");
        System.out.println("| Val 5: Lägg till en tidning i systemet.|");
        System.out.println("| Val 6: Avsluta programmet.             |");
        System.out.println("| Skriv 1-6.                             |");;
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
}