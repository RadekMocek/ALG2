package pkg02_bus;

import java.util.Scanner;

public class BusApp {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Bus bus = new Bus(41, 15, "DPMLJ");
        
        for (int i = 1; i < 5; i++) {
            
            System.out.println(i + ". zastávka");
            System.out.println(bus);
            System.out.println("Zadejte, kolik lidí chce vystoupit a kolik nastoupit:");
            int vystup = sc.nextInt();
            int nastup = sc.nextInt();
            int realVystup = bus.getOff(vystup);
            if (realVystup != vystup) System.out.println("Vysoupit mohlo jenom " + realVystup + ".");
            int realNastup = bus.getOn(nastup);
            if (realNastup != nastup) System.out.println("Nastoupit mohlo jenom " + realNastup + ".");
            System.out.println(bus + "\n");
            
        }
        
        System.out.println("5. zastávka – konečná");
        System.out.println(bus);
        bus.getOffAll();
        System.out.println("Všichni vystoupili.\n" + bus);
    }
    
}
