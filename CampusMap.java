import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Seelye Hall", "2 Seelye Dr Northampton, MA 01060", 4));
        myMap.addBuilding(new Building("Hillyer Hall", "22 Elm St Northampton, MA 01063", 4));
        House ziskindHouse = new House("Ziskind House", "1 Henshaw Ave Northampton, MA 01063", 3, true, true);
        myMap.addBuilding(ziskindHouse);
        myMap.addBuilding(new House("Park House", "134 Elm St Northampton, MA 01063", 4));
        myMap.addBuilding(new House("King House", "180 Elm St Northampton, MA 01063", 4, true, true));
        myMap.addBuilding(new House("Chase House", "45 Elm St Northampton, MA 01063", 3, true, true));
        Library neilsonLibrary = new Library("Neilson Library", "7 Neilson Drive Northampton, MA 01063", 4);
        myMap.addBuilding(neilsonLibrary);
        myMap.addBuilding(new Library("Werner Josten Performing Arts Library", "122 Green St Northampton, MA 01063", 1, false));
        Cafe campusCafe = new Cafe("Campus Center Café", "100 Elm St Northampton, MA 01063", 1);
        myMap.addBuilding(campusCafe);
        myMap.addBuilding(new Cafe("The Compass Café", "7 Neilson Drive Northampton, MA 01063", 1, 50, 30, 30, 10));
        System.out.println(myMap);

        Student piper = new Student("Piper Wurman", "9912345", 2029);
        Student scout = new Student("Scout Bookham", "9987765", 2029);
        Student[] students = {piper, scout};
        ziskindHouse.moveIn(students);
        System.out.println("Piper is a resident of Ziskind House: " + ziskindHouse.isResident(piper));
        System.out.println("Scout is a resident of Ziskind House: " + ziskindHouse.isResident(scout));

        String[] books = {"The Great Gatsby", "Calling In", "The House in the Cerulean Sea"};
        neilsonLibrary.addTitle(books);
        neilsonLibrary.checkOut(books);
        neilsonLibrary.printCollection();

        campusCafe.sellCoffee(15, 38, 38);
        campusCafe.sellCoffee(19, 2, 1);
    }
    
}
