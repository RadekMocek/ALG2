package pkg03_polynomy;

import java.util.Arrays;

public class Polynom {
    
    private int[] coeffs;
    
    private Polynom(int[] coeffs) {
        int len = coeffs.length;
        while (coeffs[len-1] == 0) {            
            if (len == 1) throw new IllegalArgumentException("Prázdný polynom");
            len--;
        }        
        this.coeffs = Arrays.copyOf(coeffs, len);
    }
    
    // Human friendly
    public static Polynom getInstanceDownwardly(int... a) {
        int len = a.length;
        int[] b = new int[len];
        for (int i = 0; i < len; i++) {
            b[len-1-i] = a[i];
        }
        return new Polynom(b);
    }
    
    // Machine friendly
    public static Polynom getInstanceUpwardly(int... a) {
        return new Polynom(a);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");        
        int len = coeffs.length;
        for (int i = len - 1; i >= 0; i--) {
            sb.append("(").append(coeffs[i]).append(")*x^").append(i);
            if (i != 0) sb.append(" + ");
        }
        return sb.toString();
    }
    
    public int getDegree() {
        return (coeffs.length - 1);
    }
    
    public int getCoefficient(int exponent) {
        if (exponent < 0 || exponent > getDegree()) throw new IllegalArgumentException("Neexistující člen");
        return (coeffs[exponent]);
    }
    
    // Debug
    public static void main(String[] args) {        
        
        Polynom pol = Polynom.getInstanceDownwardly(1,2,3);        
        System.out.println(pol);
        System.out.println("stupeň: " + pol.getDegree());
        System.out.println(pol.getCoefficient(2));
    }
}
