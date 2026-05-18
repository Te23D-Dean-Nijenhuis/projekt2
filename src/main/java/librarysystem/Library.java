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

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.magazines = new ArrayList<>();
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