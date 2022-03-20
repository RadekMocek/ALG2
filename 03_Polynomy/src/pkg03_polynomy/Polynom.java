package pkg03_polynomy;

import java.util.Arrays;

public class Polynom {
    
    private final int[] coeffs;
    private final int len;
    
    private Polynom(int[] coeffs) {
        int pointer = coeffs.length;
        // Remove trailing zeroes
        while (coeffs[pointer - 1] == 0) {            
            if (pointer == 1) {
                this.coeffs = new int[]{0};
                len = 1;
                return;
            }
            pointer--;
        }        
        this.coeffs = Arrays.copyOf(coeffs, pointer);
        len = this.coeffs.length;
    }
    
    // Tovární metoda – User friendly
    public static Polynom getInstanceDownwardly(int... a) {
        int len = a.length;
        int[] b = new int[len];
        for (int i = 0; i < len; i++) {
            b[len-1-i] = a[i];
        }
        return new Polynom(b);
    }
    
    // Tovární metoda – Coder friendly
    public static Polynom getInstanceUpwardly(int... a) {
        return new Polynom(a);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");        
        for (int i = len - 1; i >= 0; i--) {
            int coeff = coeffs[i];            
            if (coeff > 0 && i != len - 1) sb.append("+");
            sb.append(coeff);
            if (i != 0) sb.append("*x");
            if (i > 1) sb.append("^").append(i);
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public int getDegree() {
        return (len - 1);
    }
    
    public int getCoefficient(int exponent) {
        if (exponent < 0 || exponent > getDegree()) throw new IllegalArgumentException("Neexistující člen");
        return (coeffs[exponent]);
    }
    
    public int getValue(int x) {        
        int value = coeffs[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            value *= x;
            value += coeffs[i];
        }
        return value;
    }
    
    public Polynom getDerivate() {
        int[] derivate = new int[len - 1];
        for (int i = 1; i < len; i++) {
            derivate[i - 1] = i * coeffs[i];
        }
        return getInstanceUpwardly(derivate);
    }
    
    // Debug
    public static void main(String[] args) {        
        
        /*
        Polynom pol = Polynom.getInstanceDownwardly(1,-2,3,-4);        
        System.out.println(pol);
        System.out.println("stupeň: " + pol.getDegree());
        System.out.println(pol.getCoefficient(2));
        System.out.println(pol.getValue(-2));
        
        var der = pol.getDerivate();
        System.out.println(der);
        */
        
        Polynom polA = Polynom.getInstanceDownwardly(1,2,3);
        Polynom polB = Polynom.getInstanceDownwardly(5,6,7,8);
        Polynom sum = PolynomTools.Sum(polA, polB);
        Polynom mul = PolynomTools.Multiply(polA, polB);
        System.out.println(polA+"\n"+polB+"\n-----");
        System.out.println(mul);
    }
}
