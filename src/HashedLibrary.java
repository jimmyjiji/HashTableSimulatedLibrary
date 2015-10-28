import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Enumeration;
import big.data.*;

/**
 * Created by Jimmy on 4/19/2015 in PACKAGE_NAME
 * 109259420
 * Homework 5
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * The HashedLibrary class that stores books
 */
public class HashedLibrary extends Hashtable implements Serializable{
    /**
     * Adds a book to the HashedLibrary manually
     * @param title is the title of the book to be added
     * @param author is the author of the book to be added
     * @param publisher is the publisher of the book to be added
     * @param isbn is the isbn of the book to be added
     */
    public void addBook(String title, String author, String publisher, String isbn) {
        Book book = new Book(title, author, publisher, isbn);
        if (super.containsKey(isbn))
            System.out.println(isbn+ " Book already recorded");
        else {
            super.put(isbn, book);
            System.out.println(book.getIsbn() + ": " + book.getTitle() + " by " + book.getAuthor() + " recorded");
        }
    }

    /**
     * Returns the book if it exists
     * @param isbn is the key that it looks for in the HashedLibrary
     * @return the book found
     * @throws IllegalAccessException if the book is not found
     */
    public Book getBookByIsbn(String isbn) throws IllegalAccessException {
        if (super.containsKey(isbn)) {
            System.out.println("Book ISBN \t\t\tTitle                       \t\t\tAuthor                        Publisher");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            return (Book) super.get(isbn);
        } else
            throw new IllegalAccessException("Book not found");
    }

    /**
     * Adds the list of books into the HashedLibrary through a file
     * @param fileName is the file name to be added
     * @throws FileNotFoundException if the file does not exist
     */
    public void addAllBookInfo(String fileName) throws FileNotFoundException{
        Scanner input = new Scanner(new File(fileName));
        while(input.hasNext()) {
            String book = input.nextLine();
            DataSource ds = DataSource.connect("http://www.cs.stonybrook.edu/~cse214/hw/hw6/" + book + ".xml").load();
            if (super.containsKey((ds.fetchString("isbn"))))
                System.out.println(ds.fetchString("isbn")+ " Book already recorded");
            else
                addBook(ds.fetchString("title"), ds.fetchString("author"), ds.fetchString("publisher"), ds.fetchString("isbn"));

        }
    }

    /**
     * Prints the entire hashed library list through the Enumeration API
     */
    public void printCatalog() {
        System.out.println("Book ISBN \t\t\tTitle                       \t\t\tAuthor                        Publisher");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        Enumeration e = super.elements();

        while (e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
    }
}
