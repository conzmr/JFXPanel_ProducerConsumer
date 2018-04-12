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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
/**
 *
 * @author conzmr
 */
public class Buffer {
    
    private Queue<Operation> producedOperations;
    private int size;
    private TableView toDoList, doneList;
    private ObservableList<Operation> doneOperations, toDoOperations;
   
    
    Buffer(int maxSize, TableView toDoTable, TableView doneTable) {
        this.size = maxSize;
        this.producedOperations = new LinkedList<>();
        this.toDoList = toDoTable;
        this.doneList = doneTable;
        this.doneOperations = FXCollections.observableArrayList();
        this.toDoOperations = FXCollections.observableArrayList();
    }
    
    synchronized void consume(int id) {
        Operation product;
        while(toDoOperations.isEmpty()){
            try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        product = toDoOperations.remove(0); //decrementar progress bar
        product = product.setConsumer(id).solveOperation();
        doneOperations.add(product);
        doneList.setItems(doneOperations);
        notifyAll();
    }
    
    synchronized void produce(Operation product) {
        while(this.producedOperations.size() >= size) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        toDoOperations.add(product);//Incrementar progress bar
        toDoList.setItems(toDoOperations);
        notifyAll();
    }
    
}
