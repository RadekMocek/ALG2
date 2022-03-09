package moneybox;

public class MoneyBox {
    
    private String vlastnik;
    private int pctKorun;
    private int pctDvoukorun;
    
    public MoneyBox(String vlastnik) {
        this.vlastnik = vlastnik;
        pctKorun = 0;
        pctDvoukorun = 0;
    }
    
    public MoneyBox(String vlastnik, int pctKorun, int pctDvoukorun) {
        this.vlastnik = vlastnik;
        this.pctKorun = pctKorun;
        this.pctDvoukorun = pctDvoukorun;
    }
    
    public void setVlastnik(String vlastnik) {
        this.vlastnik = vlastnik;
    }
    
    public int getSum() {
        return 2*pctDvoukorun+pctKorun;
    }
    
    public void insertOneCrown() {
        pctKorun++;
    }
    
    public void insertTwoCrowns() {
        pctDvoukorun++;
    }
    
    public void insertSpecificCrowns(int korun, int dvoukorun) {
        pctKorun += korun;
        pctDvoukorun += dvoukorun;
    }
    
    @Override
    public String toString() {
        int sum = getSum();
        return String.format("%s má v pokladničce %dKč - %dx1Kč, %dx2Kč.", vlastnik, sum, pctKorun, pctDvoukorun);
    }

    public boolean isEnoughForGift(int price) {
        int sum = getSum();
        return (price <= sum);
    }

    public boolean isRicher(MoneyBox other) {
        return (other.getSum() > getSum());
    }
    
    public void wedding(MoneyBox other) {
        other.pctKorun += pctKorun;
        pctKorun = 0;
        other.pctDvoukorun += pctDvoukorun;
        pctDvoukorun = 0;
    }
    
}
