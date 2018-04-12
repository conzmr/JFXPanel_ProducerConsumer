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
public class NumberTextField extends TextField {
    
    public NumberTextField(){
        this.setPromptText("Enter only numbers");
    }
    
    @Override
    public void replaceText(int i, int i1, String string){
        if (string.matches("[0-9]") || string.isEmpty()) {
            super.replaceText(i, i1, string);
        }
    }
    
    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
    
    
    
}
