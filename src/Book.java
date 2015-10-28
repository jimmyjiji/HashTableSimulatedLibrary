import java.io.Serializable;

/**
 * Created by Jimmy on 4/19/2015 in PACKAGE_NAME
 * 109259420
 * Homework 5
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * Book class that contains a title, author, publisher and isbn.
 */
public class Book implements Serializable {

    private String title;
    private String author;
    private String publisher;
    private String isbn;

    /**
     * Constructor for book object
     * @param title is the title for the book
     * @param author is the author of the book
     * @param publisher is the publisher of the book
     * @param isbn is the ISBN of the book
     */
    Book(String title, String author, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    /**
     * return the ISBN of the book
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * sets the ISBN of the book
     * @param isbn sets the ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * return the author of the book
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * sets the author of the book
     * @param author sets the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * returns the publisher of the book
     * @return the publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * sets the publisher of the book
     * @param publisher sets the publisher of the book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * returns the title of the book
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the book
     * @param title sets the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the string representation of the book for tabular output
     * @return String of book
     */
    @Override
    public String toString(){
        return String.format("%-20s%-40s%-30s%-20s", getIsbn(), getTitle(), getAuthor(), getPublisher());
    }
}
