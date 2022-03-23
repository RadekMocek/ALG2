package pkg03_polynomy;

public final class PolynomTools {
    
    private PolynomTools() {}
    
    public static Polynom sum(Polynom a, Polynom b) {        
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
    
    public static Polynom sumAll(Polynom... pol) {
        Polynom polSum = pol[0];
        for (int i = 1; i < pol.length; i++) {
            polSum = sum(polSum, pol[i]);
        }
        return polSum;
    }
    
    public static Polynom multiply(Polynom a, Polynom b) {
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
