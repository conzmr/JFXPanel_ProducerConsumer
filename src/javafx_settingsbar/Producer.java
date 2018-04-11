/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author conzmr
 */
public class Producer extends Thread{
    private static int initialRange, finalRange;
    private static long waitingTime;
    private static Random random;
    private static Buffer buffer;
    private final int id;
    
    public static void setStaticProperties(int iRange, int fRange, long wTime, Buffer buf){
        initialRange = iRange;
        finalRange = fRange;
        waitingTime = wTime;
        buffer = buf;
        random = new Random();
    }
    
    private Operation produceOperation(){
        int a, b;
        char symbol;
        String symbols;
        symbols = "+-*/";
        System.out.println("CHAR AT 0 INDEX "+symbols.charAt(0));
        symbol = symbols.charAt(random.nextInt(4) + 1);
        a = getRandFromRange();
        b= getRandFromRange();
        return new Operation(a, b, symbol, this.id);
    }
    
    private static int getRandFromRange(){
        return random.nextInt(finalRange - initialRange) + initialRange + 1;
    }
    
    public Producer(int id) {
        this.id = id;
    }
    
    @Override
    public void run() {
        while(true){
            Operation product = produceOperation();
            try {
                buffer.produce(product); 
                Thread.sleep(waitingTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
    
   
    
}
