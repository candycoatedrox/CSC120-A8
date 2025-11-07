public class Cafe extends Building implements CafeRequirements {

    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;

    private int maxCoffeeOunces;
    private int maxSugarPackets;
    private int maxCreams;
    private int maxCups;

    /**
     * Constructor
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
        
        this.maxCoffeeOunces = nCoffeeOunces;
        this.maxSugarPackets = nSugarPackets;
        this.maxCreams = nCreams;
        this.maxCups = nCups;

        System.out.println("You have built a cafe: ☕");
    }

    /**
     * Sells one cup of coffee with the given ingredients
     * @param size the number of coffee ounces in the order
     * @param nSugarPackets the number of sugar packets in the order
     * @param nCreams the number of creams in the order
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        if (this.nCoffeeOunces < size || this.nSugarPackets < nSugarPackets || this.nCreams < nCreams || this.nCups < 1) {
            this.restock(15, 30, 30, 10);
            // calls sellCoffee recursively, so that if there *still* aren't
            // enough ingredients (somehow), the cafe is restocked again
            this.sellCoffee(size, nSugarPackets, nCreams);
        } else {
            this.nCoffeeOunces -= size;
            this.nSugarPackets -= nSugarPackets;
            this.nCreams -= nCreams;
            this.nCups -= 1;
        }
    }

    /**
     * Restocks this Cafe with a given amount of inventory
     * @param nCoffeeOunces the number of coffee ounces to add to the inventory
     * @param nSugarPackets the number of sugar packets to add to the inventory
     * @param nCreams the number of creams to add to the inventory
     * @param nCups the number of cups to add to the inventory
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces += nCoffeeOunces;
        if (this.nCoffeeOunces > this.maxCoffeeOunces) {
            this.nCoffeeOunces = this.maxCoffeeOunces;
        }

        this.nSugarPackets += nSugarPackets;
        if (this.nSugarPackets > this.maxSugarPackets) {
            this.nSugarPackets = this.maxSugarPackets;
        }
        
        this.nCreams += nCreams;
        if (this.nCreams > this.maxCreams) {
            this.nCreams = this.maxCreams;
        }
        
        this.nCups += nCups;
        if (this.nCups > this.maxCups) {
            this.nCups = this.maxCups;
        }
    }
    
    public static void main(String[] args) {
        Cafe myCafe = new Cafe("Starbucks", "1 Chapin Way", 1, 20, 40, 40, 15);
        System.out.println(myCafe.getName() + " stock: " + myCafe.nCoffeeOunces + "oz coffee, " + myCafe.nSugarPackets + " sugar packets, " + myCafe.nCreams + " creams, " + myCafe.nCups + " cups");
        myCafe.sellCoffee(3, 2, 2);
        System.out.println(myCafe.getName() + " stock: " + myCafe.nCoffeeOunces + "oz coffee, " + myCafe.nSugarPackets + " sugar packets, " + myCafe.nCreams + " creams, " + myCafe.nCups + " cups");
        myCafe.sellCoffee(15, 38, 38); // getting rid of excess stock to test restock()
        System.out.println(myCafe.getName() + " stock: " + myCafe.nCoffeeOunces + "oz coffee, " + myCafe.nSugarPackets + " sugar packets, " + myCafe.nCreams + " creams, " + myCafe.nCups + " cups");
        myCafe.sellCoffee(19, 2, 1);
        System.out.println(myCafe.getName() + " stock: " + myCafe.nCoffeeOunces + "oz coffee, " + myCafe.nSugarPackets + " sugar packets, " + myCafe.nCreams + " creams, " + myCafe.nCups + " cups");
    }
    
}
