import java.util.ArrayList;

public class House extends Building implements HouseRequirements {

    private ArrayList<Student> residents;
    private boolean hasDiningRoom;

    /**
     * Constructor
     */
    public House(String name, String address, int nFloors, boolean hasDiningRoom) {
        super(name, address, nFloors);
        this.residents = new ArrayList<>();
        this.hasDiningRoom = hasDiningRoom;

        // The little print statements in the constructors are so cute, I can't bear to get rid of them
        System.out.println("You have built a house: 🏠");
    }

    /**
     * Accessor for hasDiningRoom
     */
    public boolean hasDiningRoom() {
        return this.hasDiningRoom;
    }

    /**
     * Accessor-like method for the number of residents in this House
     * @return the number of residents in this House
     */
    public int nResidents() {
        return this.residents.size();
    }

    /**
     * Adds a new resident to this House
     * @param s the Student moving in to this House
     * @throws RuntimeException if the Student is already a resident of this House
     */
    public void moveIn(Student s) {
        if (!this.isResident(s)) {
            this.residents.add(s);
        } else {
            throw new RuntimeException("Student is already a resident of this House");
        }
    }

    /**
     * Removes a resident from this House
     * @param s the Student moving out of this House
     * @throws RuntimeException if the Student is not a resident of this House
     */
    public Student moveOut(Student s) {
        if (this.isResident(s)) {
            this.residents.remove(s);
            return s;
        } else {
            throw new RuntimeException("Student is not a resident of this House");
        }
    }

    /**
     * Checks whether a given Student is a resident of this House
     * @param s the Student to check for
     * @return true if the Student is a resident of this House; false otherwise
     */
    public boolean isResident(Student s) {
        return this.residents.contains(s);
    }

    public static void main(String[] args) {
        Student piper = new Student("Piper Wurman", "9912345", 2029);
        Student scout = new Student("Scout Bookham", "9987765", 2029);
        House myHouse = new House("Ziskind House", "1 Chapin Way", 3, true);

        System.out.println(myHouse.getName() + ", " + myHouse.getAddress() + ", " + myHouse.getFloors() + " floors, hasDiningRoom = " + myHouse.hasDiningRoom());
        System.out.println(myHouse.nResidents() + " residents");

        System.out.println(myHouse.isResident(piper));
        myHouse.moveIn(piper);
        myHouse.moveIn(scout);
        System.out.println(myHouse.isResident(piper));
        System.out.println(myHouse.nResidents() + " residents");
        if (myHouse.moveOut(piper) == piper) {
            System.out.println("Piper moved out");
        }
        System.out.println(myHouse.isResident(piper));
    }

}