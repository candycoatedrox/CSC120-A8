import java.util.Hashtable;
import java.util.Set;

public class Library extends Building implements LibraryRequirements {

    private Hashtable<String, Boolean> collection;

    /**
     * Constructor
     */
    public Library(String name, String address, int nFloors) {
        super(name, address, nFloors);
        this.collection = new Hashtable<>();

        System.out.println("You have built a library: 📖");
    }

    /**
     * Adds a book to this Library's collection
     * @param title the title of the book being added to the collection
     * @throws RuntimeException if the title is already in this Library's collection
     */
    public void addTitle(String title) {
        if (this.containsTitle(title)) {
            throw new RuntimeException("Title already in collection");
        }

        this.collection.put(title, true);
    }

    /**
     * Removes a book from this Library's collection
     * @param title the title of the book being removed from the collection
     * @throws RuntimeException if the title is not in this Library's collection
     */
    public String removeTitle(String title) {
        if (!this.containsTitle(title)) {
            throw new RuntimeException("Title not in collection");
        }

        this.collection.remove(title);
        return title;
    }

    /**
     * Checks out a book from this Library
     * @param title the title of the book being checked out
     * @throws RuntimeException if the title is not in this Library's collection or is already checked out
     */
    public void checkOut(String title) {
        if (this.containsTitle(title)) {
            if (this.isAvailable(title)) {
                this.collection.put(title, false);
            } else {
                throw new RuntimeException("Title already checked out");
            }
        } else {
            throw new RuntimeException("Title not in collection");
        }
    }

    /**
     * Returns a book to this Library
     * @param title the title of the book being returned
     * @throws RuntimeException if the title is not in this Library's collection or is not checked out
     */
    public void returnBook(String title) {
        if (this.containsTitle(title)) {
            if (!this.isAvailable(title)) {
                this.collection.put(title, true);
            } else {
                throw new RuntimeException("Title is not checked out");
            }
        } else {
            throw new RuntimeException("Title not in collection");
        }
    }

    /**
     * Checks whether a title is in this Library's collection
     * @param title the title to check for
     * @return true if the title is in this Library's collection; false otherwise
     */
    public boolean containsTitle(String title) {
        return this.collection.containsKey(title);
    }

    /**
     * Checks whether a title is currently available
     * @param title the title to check the status of
     * @return true if the title is not checked out; false otherwise
     * @throws RuntimeException if the title is not in this Library's collection
     */
    public boolean isAvailable(String title) {
        if (!this.containsTitle(title)) {
            throw new RuntimeException("Title does not exist in collection");
        }

        return this.collection.get(title);
    }

    /**
     * Prints out this Library's collection in an easy-to-read way, including availability
     */
    public void printCollection() {
        int n = 1;
        Set<String> titles = this.collection.keySet();

        for (String title : titles) {
            if (this.isAvailable(title)) {
                System.out.println(n + ". " + title + " (available)");
            } else {
                System.out.println(n + ". " + title + " (checked out)");
            }

            n += 1;
        }
    }

    public static void main(String[] args) {
        Library myLibrary = new Library("Neilson Library", "1 Chapin Way", 4);
        myLibrary.addTitle("The Great Gatsby");
        myLibrary.addTitle("Calling In");
        myLibrary.addTitle("The House in the Cerulean Sea");
        myLibrary.printCollection();

        myLibrary.checkOut("The Great Gatsby");
        System.out.println("The Great Gatsby is available: " + myLibrary.isAvailable("The Great Gatsby"));
        System.out.println("The Great Gatsby is in the library: " + myLibrary.containsTitle("The Great Gatsby"));
        System.out.println("Gideon the Ninth is in the library: " + myLibrary.containsTitle("Gideon the Ninth"));
        myLibrary.printCollection();
        myLibrary.returnBook("The Great Gatsby");

        myLibrary.removeTitle("The Great Gatsby");
        myLibrary.printCollection();
    }

}