/*
Dean Nijenhuis TE23D
Denna klassen är biblioteks klassen, den innehåller den centrala funktionaliteten för biblioteket
 */

package librarysystem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.util.Scanner;

public class Library {

    private String baseUrl = "http://10.151.168.5:3104/";
    private Gson gson = new Gson();

    private ArrayList<Book> books;
    private ArrayList<Magazine> magazines;
    private ArrayList<User> users;
    private ArrayList<SuspendedUser> suspended;
    private Map<String, Book> bookMap;
    private Map<String, Magazine> magazineMap;
    private Map<String, User> userMap;
    private Map<String, SuspendedUser> suspendedMap;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.magazines = new ArrayList<>();
        this.users = new ArrayList<>();
        this.suspended = new ArrayList<>();
        this.bookMap = new HashMap<>();
        this.magazineMap = new HashMap<>();
        this.userMap = new HashMap<>();
        this.suspendedMap = new HashMap<>();
    }

    // check users ---------------------------------

    public void checkUsers(){
        for (User i : users) {
            if(suspendedMap.containsKey(i.getId().toLowerCase())) {
                System.out.println(i.getName() + " Får ej låna!!");
            } else {
                System.out.println(i.getName() + " Får låna!!");
            }
        }
    }

    // check users ---------------------------------

    // importera all ------------------------------

    public void importAllBooks() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "books").asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        books = gson.fromJson(json_data, type);

        if (books == null) {
            books = new ArrayList<>();
        }
        for (Book i : books) {
            bookMap.put(i.getTitle().toLowerCase(), i);
        }
    }

    public void importAllMagazines() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "magazines").asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazines = gson.fromJson(json_data, type);

        if (magazines == null) {
            magazines = new ArrayList<>();
        }
        for (Magazine i : magazines) {
            magazineMap.put(i.getTitle().toLowerCase(), i);
        }
    }

    public void importAllUsers() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "users").asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();

        users = gson.fromJson(json_data, type);

        if (users == null) {
            users = new ArrayList<>();
        }
        for (User i : users) {
            userMap.put(i.getEmail().toLowerCase(), i);
        }
    }

    public void importAllSuspended() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "suspended").asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<ArrayList<SuspendedUser>>() {
        }.getType();

        suspended = gson.fromJson(json_data, type);

        if (suspended == null) {
            suspended = new ArrayList<>();
        }
        for (SuspendedUser i : suspended) {
            suspendedMap.put(i.getCustomerId().toLowerCase(), i);
        }

    }   

    // importera all ------------------------------

    // importera id -------------------------------

    public void importBookById(String id) { // hämta en bok från server utifrån id
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "books/" + id).asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<Book>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
            // tomt obejekt
            Book e = gson.fromJson(json_data, type);
            books.add(e);
            bookMap.put(e.getTitle().toLowerCase(), e);
            System.out.println("Hämtade boken: " + e);
        } else {
            System.out.println("Denna boken finns inte!");
        }

    }

    public void importMagazineById(String id) { // hämta en tidning från server utifrån id
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "magazines/" + id).asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<Magazine>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
                                           // tomt obejekt
            Magazine e = gson.fromJson(json_data, type);
            magazines.add(e);
            magazineMap.put(e.getTitle().toLowerCase(), e);
            System.out.println("Hämtade tidningen: " + e);
        } else {
            System.out.println("Denna tidningen finns inte!");
        }

    }

    public void importUserById(String id) { // hämta en tidning från server utifrån id
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "users/" + id).asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<User>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
                                           // tomt obejekt
            User e = gson.fromJson(json_data, type);
            users.add(e);
            userMap.put(e.getEmail().toLowerCase(), e);
            System.out.println("Hämtade användaren: " + e);
        } else {
            System.out.println("Denna användaren finns inte!");
        }

    }

    public void importSuspendedById(String id) { // hämta en tidning från server utifrån id
        HttpResponse<String> response;
        try {
            response = Unirest.get(baseUrl + "users/" + id).asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        String json_data = response.getBody();

        Type type = new TypeToken<SuspendedUser>() {
        }.getType();

        if (response.getStatus() == 200) { // checkar att status koden är 200 innan den lägger till annars blir det ett
                                           // tomt obejekt
            SuspendedUser e = gson.fromJson(json_data, type);
            suspended.add(e);
            suspendedMap.put(e.getCustomerId().toLowerCase(), e);
            System.out.println("Hämtade avstängda användaren: " + e);
        } else {
            System.out.println("Denna avstängda användaren finns inte!");
        }

    }

    // importera id -------------------------------

    // hitta saker ---------------------------------

    public void findBook(String Title) {
        Book e = bookMap.get(Title);

        if (e != null) {
            System.out.println(e);
        } else {
            System.out.println("Boken hittades inte!");
        }

    }

    public void findMagazine(String Title) {
        Magazine e = magazineMap.get(Title);

        if (e != null) {
            System.out.println(e);
        } else {
            System.out.println("Tidningen hittades inte!");
        }

    }

    public void findUser(String email) {
        User e = userMap.get(email);

        if (e != null) {
            System.out.println(e);
        } else {
            System.out.println("Användaren hittades inte!");
        }

    }

    public void findSuspended(String id) {
        SuspendedUser e = suspendedMap.get(id);

        if (e != null) {
            System.out.println(e);
        } else {
            System.out.println("avstängda användaren hittades inte!");
        }

    }

    // hitta saker ---------------------------------

    // ta bort saker -------------------------------

    public void deleteBook(String title) {
        int status;
        String id = bookMap.get(title).id;

        try {
            status = Unirest.delete(baseUrl + "books/" + id)
                    .asEmpty()
                    .getStatus();
        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (status == 200) {
            System.out.println("Boken: " + title + " är borttagen!!");
        } else if (status == 204) {
            System.out.println("finns ingen bok med den titeln");
        } else {
            System.out.println("Något gick fel. statuskod: " + status);
        }
    }

    public void deleteMagazine(String title) {
        int status;
        String id = magazineMap.get(title).id;

        try {
            status = Unirest.delete(baseUrl + "magazines/" + id)
                    .asEmpty()
                    .getStatus();
        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (status == 200) {
            System.out.println("tidningen: " + title + " är borttagen!!");
        } else if (status == 204) {
            System.out.println("finns ingen tidning med den titeln");
        } else {
            System.out.println("Något gick fel. statuskod: " + status);
        }
    }

    public void deleteUser(String email) {
        int status;
        String id = userMap.get(email).getId();

        try {
            status = Unirest.delete(baseUrl + "users/" + id)
                    .asEmpty()
                    .getStatus();
        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (status == 200) {
            System.out.println("Användaren: " + email + " är borttagen!!");
        } else if (status == 204) {
            System.out.println("finns ingen användare med den emailen");
        } else {
            System.out.println("Något gick fel. statuskod: " + status);
        }
    }

    public void deleteSuspended(String id) {
        int status;
        try {
            status = Unirest.delete(baseUrl + "suspended/" + id)
                    .asEmpty()
                    .getStatus();
        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (status == 200) {
            System.out.println("Avstängda användaren med id: " + id + " är borttagen!!");
        } else if (status == 204) {
            System.out.println("finns ingen avstängd användare med detta id");
        } else {
            System.out.println("Något gick fel. statuskod: " + status);
        }
    }

    // ta bort saker -------------------------------

    // skriv ut saker-------------------------------------

    public void printSortedBooks() {
        ArrayList<Book> e = new ArrayList<>(books);
        Collections.sort(e);

        for (Book i : e) {
            System.out.println(i);
        }
    }

    public void printSortedMagazines() {
        ArrayList<Magazine> e = new ArrayList<>(magazines);
        Collections.sort(e);

        for (Magazine i : e) {
            System.out.println(i);
        }
    }

    public void printSortedUsers() {
        ArrayList<User> e = new ArrayList<>(users);
        Collections.sort(e);

        for (User i : e) {
            System.out.println(i);
        }
    }

    // skriv ut saker-------------------------------------

    // avgör om får långa -------------------------------------

    public void borrowCheck() {

    }

    // avgör om får långa -------------------------------------

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

    // skapa saker ----------------------------------------

    public void addBook(Scanner keyboard) {
        System.out.println("Ange titel:");
        String title = Main.readString(keyboard);

        boolean isAvailable = true;

        System.out.println("Ange författare:");
        String author = Main.readString(keyboard);

        System.out.println("Ange genre:");
        String genre = Main.readString(keyboard);

        System.out.println("Ange antal sidor:");
        int pages = Main.readInt(keyboard);

        Book a = new Book("0", title, isAvailable, author, genre, pages);

        HttpResponse<String> response;
        String jsonBody = gson.toJson(a);
        try {
            response = Unirest.post(baseUrl + "books")
                    .header("Content-type", "application/json")
                    .body(jsonBody)
                    .asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (response.getStatus() != 200 && response.getStatus() != 201) {
            System.out.println("Error kod" + response.getStatus());
            return;
        }
        System.out.println("Sparat på servern: " + gson.fromJson(response.getBody(), Book.class));

    }

    public void addMagazine(Scanner keyboard) {

        System.out.println("Ange titel:");
        String title = Main.readString(keyboard);

        boolean isAvailable = true;

        System.out.println("Ange issue number:");
        int issueNumber = Main.readInt(keyboard);

        System.out.println("Ange kategori:");
        String category = Main.readString(keyboard);

        System.out.println("Ange publiceringsår:");
        int publishedYear = Main.readInt(keyboard);

        Magazine m = new Magazine("0", title, isAvailable, issueNumber, category, publishedYear);

        HttpResponse<String> response;

        try {
            response = Unirest.post(baseUrl + "magazines")
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(m))
                    .asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (response.getStatus() != 200 && response.getStatus() != 201) {
            System.out.println("Error kod " + response.getStatus());
            return;
        }

        System.out.println("Sparat på servern: " +
                gson.fromJson(response.getBody(), Magazine.class));
    }

    public void addUser(Scanner keyboard) {

        System.out.println("Ange namn:");
        String name = Main.readString(keyboard);

        System.out.println("Ange email:");
        String email = Main.readString(keyboard);

        User u = new User("0", name, email);

        HttpResponse<String> response;

        try {
            response = Unirest.post(baseUrl + "users")
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(u))
                    .asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (response.getStatus() != 200 && response.getStatus() != 201) {
            System.out.println("Error kod " + response.getStatus());
            return;
        }

        System.out.println("Sparat på servern: " +
                gson.fromJson(response.getBody(), User.class));
    }

    public void addSuspendedUser(Scanner keyboard) {

        System.out.println("Ange customer id:");
        String customer_id = Main.readString(keyboard);

        SuspendedUser s = new SuspendedUser("0", customer_id);

        HttpResponse<String> response;

        try {
            response = Unirest.post(baseUrl + "suspended")
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(s))
                    .asString();

        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return;
        }

        if (response.getStatus() != 200 && response.getStatus() != 201) {
            System.out.println("Error kod " + response.getStatus());
            return;
        }

        System.out.println("Sparat på servern: " +
                gson.fromJson(response.getBody(), SuspendedUser.class));
    }

    // skapa saker ----------------------------------------

}