/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar.custom;

import javafx.scene.control.TextField;

/**
 *
 * @author conzmr
 */
public class NaturalNumbersTextField extends NumberTextField {

    public NaturalNumbersTextField(){
        this.setPromptText("Enter only natural numbers");
    }
    
      @Override
    public void replaceText(int i, int i1, String string){
        if(i==0){
            if (string.matches("[1-9]") || string.isEmpty()) {
                super.replaceText(i, i1, string);
           }
        }
        else {
            super.replaceText(i, i1, string);
        }
    }
    
    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
    
}
