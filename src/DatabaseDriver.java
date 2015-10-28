import java.io.*;
import java.util.Scanner;

/**
 * Created by Jimmy on 4/20/2015 in PACKAGE_NAME
 * 109259420
 * Homework 5
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * DatabaseDriver class that runs the library
 */
public class DatabaseDriver implements Serializable{
    /**
     * Main method
     * @param args runs the method
     * @throws IOException for invalid inputs
     * @throws ClassNotFoundException for class not found
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        HashedLibrary myLibrary;
        myLibrary = readFromDisk();
        //infinite loop until q
        while (true) {
            try {
                run(myLibrary);
            } catch (FileNotFoundException ex) {
                System.out.println("\nFile not found\n");
            } catch (IOException ex) {
                System.out.println("\nInvalid input\n");
            } catch (IllegalArgumentException ex) {
                System.out.println("\nInvalid input\n");
            } catch (IllegalAccessException ex) {
                System.out.println("\nBook not found\n");
            } catch (Exception ex) {
                System.out.println("\nInvalid Input\n");
            }
        }
    }

    /**
     * Writes existing info into the disk
     * @param myLibrary is the disk to be written upon
     */
    public static void writeToDisk(HashedLibrary myLibrary) {
        try {
            FileOutputStream file = new FileOutputStream("library.obj");
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(myLibrary); //Writes myLibrary to library.obj
            fout.close();
        } catch (IOException e){
            System.out.println("Invalid input");
        }
    }

    /**
     * Reads from a disk and creates a hashed library accordingly
     * @return the newly created hashed library
     * @throws ClassNotFoundException if the class is not found for ObjectInputStream
     */
    public static HashedLibrary readFromDisk() throws ClassNotFoundException{
        HashedLibrary myLibrary;
        try {
            FileInputStream file = new FileInputStream("library.obj");
            ObjectInputStream fin  = new ObjectInputStream(file);
            myLibrary = (HashedLibrary) fin.readObject(); //readObject() returns Object, so must typecast to HashedLibrary
            System.out.println("Found file library.obj");
            fin.close();
        } catch(IOException ex){
            myLibrary = new HashedLibrary();
            System.out.println("File not found. Using new HashedLibrary");
        }

        return myLibrary;
    }

    /**
     * runs the menu selection
     * @param library is the library where the menu is run
     * @throws IOException for invalid inputs
     * @throws IllegalAccessException when a book doesn't exist
     */
    public static void run(HashedLibrary library) throws IOException, IllegalAccessException {
        Scanner input = new Scanner(System.in);
        System.out.print("(D) Displays Books\n" +
                "(G) Get Book\n" +
                "(L) Load File\n" +
                "(R) Record Book\n" +
                "(Q) Quit \n" +
                "Selection: ");

        char c = input.next().toLowerCase().toCharArray()[0];
        switch (c) {
            case 'd': library.printCatalog(); break;
            case 'g':
                System.out.print("Enter book ISBN:");
                String isbn = input.next();
                System.out.println(library.getBookByIsbn(isbn)); break;
            case 'l':
                System.out.print("Enter the file to load: ");
                input.nextLine();
                String file = input.nextLine();
                library.addAllBookInfo(file); break;
            case 'r':
                System.out.print("Enter the title:");
                input.nextLine();
                String title = input.nextLine();
                System.out.print("Enter the author:");
                String author = input.nextLine();
                System.out.print("Enter the publisher:");
                String publisher = input.nextLine();
                System.out.print("Enter the ISBN:");
                String isbnNew = input.nextLine();
                library.addBook(title, author, publisher, isbnNew); break;
            case 'q':
                System.out.println("Saving and exiting");
                writeToDisk(library);
                System.exit(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid input");

        }
    }
}
