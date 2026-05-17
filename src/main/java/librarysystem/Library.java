package librarysystem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

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

        HttpResponse<String> response =
                Unirest.get(baseUrl + "books").asString();

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<Book>>() {}.getType();

        books = gson.fromJson(json_data, type);

        if (books == null) {
            books = new ArrayList<>();
        }
    }

    // hämta magazines från API
    public void importAllMagazines() {

        HttpResponse<String> response =
                Unirest.get(baseUrl + "magazines").asString();

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<Magazine>>() {}.getType();

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
//-----------------------------------------
    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }
    //printar ut alla böcker
    public void printBooks(){
        for (Book i: books) {
            System.out.println(i);
        }
    }
    //printar ut alla böcker
    public void printMagazines(){
        for (Magazine i: magazines) {
            System.out.println(i);
        }
    }
}