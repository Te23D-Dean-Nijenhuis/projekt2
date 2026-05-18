/*
Dean Nijenhuis TE23D
Denna klassen är biblioteks klassen, den innehåller den centrala funktionaliteten för biblioteket
 */

package librarysystem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Scanner;

public class Library {

    private String baseUrl = "http://localhost:3000/";
    private Gson gson = new Gson();

    private ArrayList<Book> books;
    private ArrayList<Magazine> magazines;
    private ArrayList<User> users;
    private ArrayList<SuspendedUser> suspended;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.magazines = new ArrayList<>();
        this.users = new ArrayList<>();
        this.suspended = new ArrayList<>();
    }

    // hämta böcker från API
    public void importAllBooks() {

        HttpResponse<String> response = Unirest.get(baseUrl + "books").asString();

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        books = gson.fromJson(json_data, type);

        if (books == null) {
            books = new ArrayList<>();
        }
    }

    // hämta magazines från API
    public void importAllMagazines() {

        HttpResponse<String> response = Unirest.get(baseUrl + "magazines").asString();

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazines = gson.fromJson(json_data, type);

        if (magazines == null) {
            magazines = new ArrayList<>();
        }
    }

    public void importAllUsers() {

        HttpResponse<String> response = Unirest.get(baseUrl + "users").asString();

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();

        users = gson.fromJson(json_data, type);

        if (users == null) {
            users = new ArrayList<>();
        }
    }

    public void importAllSuspended() {

        HttpResponse<String> response = Unirest.get(baseUrl + "suspended").asString();

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<SuspendedUser>>() {
        }.getType();

        suspended = gson.fromJson(json_data, type);

        if (suspended == null) {
            suspended = new ArrayList<>();
        }
    }

    public void importBookById(String id) { // hämta en bok från server utifrån id
        HttpResponse<String> response = Unirest.get(baseUrl + "books/" + id).asString();

        String json_data = response.getBody();

        Type type = new TypeToken<Book>() {
        }.getType();
        
        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
            // tomt obejekt
            Book e = gson.fromJson(json_data, type);
            books.add(e);
            System.out.println("Hämtade boken: " + e);
        } else {
            System.out.println("Denna boken finns inte!");
        }

    }

    public void importMagazineById(String id) { // hämta en tidning från server utifrån id
        HttpResponse<String> response = Unirest.get(baseUrl + "magazines/" + id).asString();

        String json_data = response.getBody();

        Type type = new TypeToken<Magazine>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
                                           // tomt obejekt
            Magazine e = gson.fromJson(json_data, type);
            magazines.add(e);
            System.out.println("Hämtade tidningen: " + e);
        } else {
            System.out.println("Denna tidningen finns inte!");
        }

    }

    public void importUserById(String id) { // hämta en tidning från server utifrån id
        HttpResponse<String> response = Unirest.get(baseUrl + "users/" + id).asString();

        String json_data = response.getBody();

        Type type = new TypeToken<User>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
                                           // tomt obejekt
            User e = gson.fromJson(json_data, type);
            users.add(e);
            System.out.println("Hämtade användaren: " + e);
        } else {
            System.out.println("Denna användaren finns inte!");
        }

    }

    public void importSuspendedById(String id) { // hämta en tidning från server utifrån id
        HttpResponse<String> response = Unirest.get(baseUrl + "suspended/" + id).asString();

        String json_data = response.getBody();

        Type type = new TypeToken<SuspendedUser>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
                                           // tomt obejekt
            SuspendedUser e = gson.fromJson(json_data, type);
            suspended.add(e);
            System.out.println("Hämtade avstängda användaren: " + e);
        } else {
            System.out.println("Denna avstängda användaren finns inte!");
        }

    }


    // Debug metoder------------------------
    public void printBookCount() {
        System.out.println("Books: " + books.size());
    }

    public void printMagazineCount() {
        System.out.println("Magazines: " + magazines.size());
    }

    // -----------------------------------------
    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    // printar ut alla böcker
    public void printBooks() {
        for (Book i : books) {
            System.out.println(i);
        }
    }

    // printar ut alla böcker
    public void printMagazines() {
        for (Magazine i : magazines) {
            System.out.println(i);
        }
    }

    public void addBook(Scanner keyboard) {
        String id = Integer.toString(books.size() + 1); // ser till att id blir nästa, ex om det finns 100 böcker så
                                                        // kommer nästa bli 101
        System.out.println("Ange titel:");
        String title = Main.readString(keyboard);

        boolean isAvailable = true;

        System.out.println("Ange författare:");
        String author = Main.readString(keyboard);

        System.out.println("Ange genre:");
        String genre = Main.readString(keyboard);

        System.out.println("Ange antal sidor:");
        int pages = Main.readInt(keyboard);

        Book e = new Book(id, title, isAvailable, author, genre, pages);

        books.add(e);
    }

    public void addMagazine(Scanner keyboard) {
        String id = Integer.toString(magazines.size() + 1);

        System.out.println("Ange titel:");
        String title = Main.readString(keyboard);

        boolean isAvailable = true;

        System.out.println("Ange upplaga:");
        int issueNumber = Main.readInt(keyboard);

        System.out.println("Ange kategori:");
        String category = Main.readString(keyboard);

        System.out.println("Ange år:");
        int publishedYear = Main.readInt(keyboard);

        Magazine e = new Magazine(id, title, isAvailable, issueNumber, category, publishedYear);
        magazines.add(e);
    }
}