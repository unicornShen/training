package random;

import java.math.BigDecimal;
import java.util.Random;

/**
 *
 */
public class TestRandom {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
//            System.out.println(Math.random());
//            BigDecimal b = new BigDecimal(Math.random()).multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
//            System.out.println(b.intValue());
            
//            System.out.println(Math.random() * 42 + 1);
        }
        
        
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            System.out.println(r.nextInt(2)+1);
        }
    }
    
}
