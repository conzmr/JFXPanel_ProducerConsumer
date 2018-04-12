/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

/**
 *
 * @author conzmr
 */
public class Operation {
    
    private int a, b;
    private char symbol;
    private String result, operation, producerId, consumerId;
    
    public Operation(int a, int b, char symbol, int producerId){
        this.a = a;
        this.b = b;
        this.symbol = symbol;
        this.producerId = String.valueOf(producerId);
        this.operation = "( " + symbol + " " + a + " " + b + " )";
    }
    
     public Operation solveOperation(){
        try{
            switch(this.symbol){
            case '+': 
                this.result = String.valueOf(this.a + this.b);
                break;
                
            case '-': 
                this.result = String.valueOf(this.a - this.b);
                break;
            
            case '*':
                this.result = String.valueOf(this.a * this.b);
                break;
                
            case '/':
                this.result = String.valueOf(Math.round(this.a / this.b)/100);
                break;
        } 
        }catch(ArithmeticException ae) {
                this.result = "Undefined";
        }
        return this;
    }
     
     public String getConsumer(){
         return this.consumerId;
     }
     
     public String getProducer(){
         return this.producerId;
     }
     
     public String getResult(){
         return this.result;
     }
     
     public Operation setConsumer(int id){
         this.consumerId = id+"";
         return this;
     }
     
     public String getOperation(){
         return "( " + this.symbol + " " + this.a + " " + this.b + " )";
     }
     
     public String toString(){
         return this.getConsumer()+this.getProducer()+this.getResult()+this.getOperation();
     }
    
}
