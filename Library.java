import java.util.Hashtable;
import java.util.Set;

public class Library extends Building implements LibraryRequirements {

    private Hashtable<String, Boolean> collection;
    private boolean hasElevator;

    /**
     * Constructor
     */
    public Library(String name, String address, int nFloors, boolean hasElevator) {
        super(name, address, nFloors);
        this.collection = new Hashtable<>();
        this.hasElevator = hasElevator;

        System.out.println("You have built a library: 📖");
    }

    /**
     * Constructor, assuming this Library has an elevator by default
     */
    public Library(String name, String address, int nFloors) {
        this(name, address, nFloors, true);
    }

    /**
     * Accessor for hasElevator
     */
    public boolean hasElevator() {
        return this.hasElevator;
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
     * Adds several books to this Library's collection
     * @param titles an array of titles to add to the collection
     * @throws RuntimeException if any of the titles are already in this Library's collection
     */
    public void addTitle(String[] titles) {
        for (String title : titles) {
            this.addTitle(title);
        }
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
     * Checks out several books from this Library
     * @param titles an array of titles to check out
     * @throws RuntimeException if any of the titles are not in this Library's collection or are already checked out
     */
    public void checkOut(String[] titles) {
        for (String title : titles) {
            this.checkOut(title);
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

        System.out.println(this.getName() + "'s Collection:");
        for (String title : titles) {
            if (this.isAvailable(title)) {
                System.out.println("  " + n + ". " + title + " (available)");
            } else {
                System.out.println("  " + n + ". " + title + " (checked out)");
            }

            n += 1;
        }
    }

    /**
     * Prints out the options available to the user in this Library
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + addTitle(title) \n + addTitle(titles) \n + removeTitle(title) \n + checkOut(title) \n + returnBook(title) \n + printCollection()");
    }

    /**
     * Moves to another floor in this Library
     */
    @Override
    public void goToFloor(int floorNum) {
        if (!this.hasElevator) {
            if (floorNum < this.activeFloor - 1 || floorNum > this.activeFloor + 1) {
                throw new RuntimeException("You can only move to an adjacent floor. You are currently on floor " + this.activeFloor + ".");
            }
        }

        super.goToFloor(floorNum);
    }

    public static void main(String[] args) {
        Library myLibrary = new Library("Neilson Library", "1 Chapin Way", 4, true);
        System.out.println(myLibrary);
        myLibrary.showOptions();

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