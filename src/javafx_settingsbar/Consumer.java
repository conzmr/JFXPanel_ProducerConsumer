/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author conzmr
 */
public class Consumer extends Thread{
    
    private final int id;
    private static long waitingTime;
    private static Buffer buffer;
    private static Queue<Operation> consumedOperations;
    
    public static void setStaticProperties(long wTime, Buffer sharedBuffer){
        waitingTime = wTime;
        buffer = sharedBuffer;
        consumedOperations = new LinkedList<>();
    }
    
    Consumer(int id) {
        this.id = id;
    }
    
     @Override
    public void run() {
        Operation product;
        while(true){
            try {
                product = buffer.consume().solveOperation(this.id);
                consumedOperations.offer(product); //Meterlas a lista de Done 
                System.out.println("Consumer "+product.getConsumer()+" consumed operation: "+product.getOperation()+" = "+product.getResult());
                Thread.sleep(waitingTime);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    
}
