package pkg03_polynomy;

public class PolynomTools {
    
    public static Polynom Sum(Polynom a, Polynom b) {        
        int lenA = a.getDegree() + 1;
        int lenB = b.getDegree() + 1;
        
        int len = Math.max(lenA, lenB);
        int[] sum = new int[len];
        
        for (int i = 0; i < len; i++) {            
            sum[i] = 0;
            if (i < lenA) sum[i] += a.getCoefficient(i);
            if (i < lenB) sum[i] += b.getCoefficient(i);
        }        
        
        return Polynom.getInstanceUpwardly(sum);
    }
    
    public static Polynom Multiply(Polynom a, Polynom b) {
        int lenA = a.getDegree() + 1;
        int lenB = b.getDegree() + 1;
        
        int len = lenA + lenB - 1;
        int[] mul = new int[len];
        for (int i = 0; i < len; i++) mul[i] = 0;
        
        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j++) {
                mul[i + j] += a.getCoefficient(i) * b.getCoefficient(j);
            }
        }
        
        return Polynom.getInstanceUpwardly(mul);
    }
}
