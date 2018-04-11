/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author conzmr
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView btnSettings, 
                      btnTasks,
                      btnShutdown; 
    
    @FXML
    private AnchorPane settingsPane, 
                       tasksPane;
    
    @FXML
    private Slider bufferSize;
    
    @FXML
    private Label bufferLabel;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private TextField consumersQty, 
                      producersQty, 
                      pWaitingTime,
                      cWaitingTime,
                      initialRange, 
                      finalRange;
    


   
    @FXML
    private void handleButtonAction(MouseEvent event) {
   
        if (event.getTarget() == btnSettings) {
            settingsPane.setVisible(true);
            tasksPane.setVisible(false);
        }
        else if(event.getTarget() == btnTasks){
                tasksPane.setVisible(true);
                settingsPane.setVisible(false);
        }
        else if(event.getTarget() == btnShutdown){
                tasksPane.setVisible(false);
                settingsPane.setVisible(false);
        }
    }

    public FXMLDocumentController() {
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         bufferSize.valueProperty().addListener((observable, oldValue, newValue) -> {
            bufferLabel.setText(Integer.toString(newValue.intValue()));
        });
         
         
         consumersQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*") ) {
                    consumersQty.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    
    public void cancel(){
        tasksPane.setVisible(false);
        settingsPane.setVisible(false);
    }

    public void start() {
        int consQty = Integer.parseInt(consumersQty.getText());
        int consWaitingTime = Integer.parseInt(cWaitingTime.getText());
        int prodQty = Integer.parseInt(producersQty.getText());
        int prodWaitingTime = Integer.parseInt(pWaitingTime.getText());
        int bSize = (int) bufferSize.getValue();
        int iRange = Integer.parseInt(initialRange.getText());
        int fRange = Integer.parseInt(finalRange.getText());
        System.out.println("Holiwis");
    }       
    
}
