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
public class Buffer {
    
    private Queue<Operation> producedOperations;
    private int size;
    
    Buffer(int maxSize) {
        this.size = maxSize;
        this.producedOperations = new LinkedList<>();
    }
    
    synchronized Operation consume() {
        Operation product;
        while(this.producedOperations.isEmpty()){
            try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        product = this.producedOperations.poll(); //Sacarlas a lista de toDo - decrementar progress bar
        notifyAll();
        return product;
    }
    
    synchronized void produce(Operation product) {
        while(this.producedOperations.size() >= size) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Producer "+product.getProducer()+" produced operation: "+product.getOperation());
        this.producedOperations.offer(product); //Meterlas a lista de toDo - incrementar progress bar
        notifyAll();
    }
    
}
