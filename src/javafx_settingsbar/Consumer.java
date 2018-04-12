/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

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
    
    public static void setStaticProperties(long wTime, Buffer sharedBuffer){
        waitingTime = wTime;
        buffer = sharedBuffer;
    }
    
    Consumer(int id) {
        this.id = id;
    }
    
     @Override
    public void run() {
        while(true){
            try {
                buffer.consume(this.id); 
                Thread.sleep(waitingTime);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    
}
