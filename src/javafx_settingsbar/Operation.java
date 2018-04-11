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
    
    private int a, b, producerId, consumerId;
    private char symbol;
    private String result;
    
    public Operation(int a, int b, char symbol, int producerId){
        this.a = a;
        this.b = b;
        this.symbol = symbol;
        this.producerId = producerId;
    }
    
     public Operation solveOperation(int consumerId){
        this.consumerId = consumerId;
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
     
     public int getConsumer(){
         return this.consumerId;
     }
     
     public int getProducer(){
         return this.producerId;
     }
     
     public String getResult(){
         return this.result;
     }
     
     public String getOperation(){
         return "( " + this.symbol + " " + this.a + " " + this.b + " )";
     }
    
}
