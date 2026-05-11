package librarysystem;

public class Book extends LibraryItem {
    private String Author;
    private String genre;
    private int pages;

    public Book(String id, String title, boolean isAvailable, String author, String genre, int pages) {
        super(id, title, isAvailable);
        this.Author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public String getAuthor() {
        return Author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

}
