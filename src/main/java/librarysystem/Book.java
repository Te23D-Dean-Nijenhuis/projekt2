/*
Dean Nijenhuis TE23D
Denna klassen är som det låter, en Book 
 */


package librarysystem;

public class Book extends LibraryItem implements Comparable<Book> {
    private String author;
    private String genre;
    private int pages;

    public Book(String id, String title, boolean isAvailable, String author, String genre, int pages) {
        super(id, title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book [Author=" + author + ", genre=" + genre + ", pages=" + pages + ", id=" + id + ", title=" + title
                + ", isAvailable=" + isAvailable + "]";
    }
    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

}
