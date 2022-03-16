package pkg02_bus;

public class Bus {

    private final int seats;
    private int route;
    private String company;
    private int passengers;
    
    public Bus(int seats, int route, String company) {
        this.seats = seats;
        this.route = route;
        this.company = company;
        passengers = 0;
    }
    
    public Bus(int seats) {
        this.seats = seats;
        route = 0;   
        company = "";
        passengers = 0;
    }
    
    public Bus() {
        seats = 40;
        route = 0;   
        company = "";
        passengers = 0;        
    }

    public int getSeats() {
        return seats;
    }

    public int getRoute() {
        return route;
    }

    public String getCompany() {
        return company;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("Autobus číslo %d společnosti %s s počtem sedadel %d veze %d cestujících.", route, company, seats, passengers);
    }
    
    /**
     * Výstup pasažérů
     * @param count
     * @return Vrací počet pasažérů, kteří skutečně vystoupili.
     */
    public int getOff(int count) {
        int realCount = (count > passengers) ? passengers : count;
        passengers -= realCount;
        return realCount;
    }
    
    /**
     * Nástup pasažérů
     * @param count
     * @return Vrací počet pasažérů, kteří skutečně nastoupili.
     */
    public int getOn(int count) {
        int freeSeats = seats - passengers;
        int realCount = (count > freeSeats) ? freeSeats : count;
        passengers += realCount;
        return realCount;
    }
    
    public void getOffAll() {
        passengers = 0;
    }
}
