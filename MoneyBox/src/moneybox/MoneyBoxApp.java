package moneybox;

public class MoneyBoxApp {
   
    public static void main(String[] args) {
        
        MoneyBox alice = new MoneyBox("Alice", 20, 20);
        MoneyBox bob = new MoneyBox("Bob");
        
        bob.insertOneCrown();
        bob.insertOneCrown();
        bob.insertTwoCrowns();
        bob.insertTwoCrowns();
        bob.insertTwoCrowns();
        bob.insertSpecificCrowns(5, 10);
        
        System.out.println("Alice:\t" + alice.getSum());
        System.out.println("Bob:\t" + bob.getSum());
        System.out.println();
        System.out.println(alice);
        System.out.println(bob);
        System.out.println();
        System.out.println("Alice:\t" + alice.isEnoughForGift(39));
        System.out.println("Bob:\t" + bob.isEnoughForGift(39));
        System.out.println();
        System.out.println("Alice je bohatší než Bob:\t" + bob.isRicher(alice));
        System.out.println("Bob je bohatší než Alice:\t" + alice.isRicher(bob));
        System.out.println("\nSvatba");
        bob.wedding(alice);
        System.out.println(alice);
        System.out.println(bob);
    }
    
}
