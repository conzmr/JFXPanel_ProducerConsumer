/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author conzmr
 */
public class Consumer extends Thread{
    
    private final int id;
    private final long waitingTime;
    private final Buffer buffer;
    private final Operation[] doneOperations;
    
    Consumer(int id, long time, Buffer buffer, Operation[] doneOperations) {
        this.id = id;
        this.waitingTime = time;
        this.buffer = buffer; 
        this.doneOperations = doneOperations;
    }
    
     @Override
    public void run() {
        Operation product;
        
        while(true){
            try {
                product = this.buffer.consume().solveOperation(this.id);
                //Agregar a la tabla tipo doneOperations.push(doneOperations); this.frame.addDone(task);
                Thread.sleep(this.waitingTime);
                // Remover toDo en this.buffer.consume() this.frame.removeTodo();
                try {
                Thread.sleep(this.milliseconds);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
   
    
}
