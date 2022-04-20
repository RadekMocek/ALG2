package inheritance;

public abstract class Shape {
    
    protected String name = "Default shape";
    
    // Přepoužití kódu v potomcích
    public String getShapeName() {
        return this.getClass().getSimpleName();
    }
    
    // Je třeba překrýt v potomcích
    public abstract double area();
        
    
    public abstract double circum();
    

    // Přkrývá toString třídy object a může být překrytá v potomcích
    @Override
    public String toString() {
        return name + " : " + getShapeName();
    }    
    
}
