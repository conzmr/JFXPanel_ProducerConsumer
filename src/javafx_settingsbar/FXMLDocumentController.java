/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_settingsbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private Button btnCancel,
                   btnStart;
    
    @FXML
    private TextField consumersQty, 
                      producersQty, 
                      pWaitingTime,
                      cWaitingTime,
                      initialRange, 
                      finalRange;
    
    @FXML
    private TableView toDoTable, doneTable;
    
    @FXML
    private TableColumn col_producerId, col_consumerId, col_result, col_operation, col2_operation, col2_producerId;
    
    @FXML
    private ProgressBar bufferContent;
   


    
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
        col_producerId.setCellValueFactory( new PropertyValueFactory<Operation,String>("producerId"));
        col_consumerId.setCellValueFactory( new PropertyValueFactory<Operation,String>("consumerId"));
        col_result.setCellValueFactory( new PropertyValueFactory<Operation, String>("result"));
        col_operation.setCellValueFactory( new PropertyValueFactory<Operation,String>("operation"));
        
        col2_operation.setCellValueFactory( new PropertyValueFactory<Operation,String>("operation"));
        col2_producerId.setCellValueFactory( new PropertyValueFactory<Operation,String>("producerId"));
        
         bufferSize.valueProperty().addListener((observable, oldValue, newValue) -> {
            bufferLabel.setText(Integer.toString(newValue.intValue()));
        });
         
        addTextLimiter(consumersQty, 3);
        addTextLimiter(producersQty, 3);
        addTextLimiter(pWaitingTime, 6);
        addTextLimiter(cWaitingTime, 6);
        addTextLimiter(initialRange, 5);
        addTextLimiter(finalRange, 5);

    }
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
    
    public void cancel(){
        tasksPane.setVisible(false);
        settingsPane.setVisible(false);
    }
    
    public boolean validateEmptyInputs(){
        return !(consumersQty.getText().trim().isEmpty() || 
                producersQty.getText().trim().isEmpty() || 
                cWaitingTime.getText().trim().isEmpty() || 
                pWaitingTime.getText().trim().isEmpty() || 
                initialRange.getText().trim().isEmpty() || 
                finalRange.getText().trim().isEmpty());
    }
    
    public boolean validateRange(){
        return !(Integer.parseInt(finalRange.getText()) <= Integer.parseInt(initialRange.getText()));
    }

    public void start() {
        if(validateEmptyInputs()){
            if(validateRange()){
                int consQty = Integer.parseInt(consumersQty.getText());
                int consWaitingTime = Integer.parseInt(cWaitingTime.getText());
                int prodQty = Integer.parseInt(producersQty.getText());
                int prodWaitingTime = Integer.parseInt(pWaitingTime.getText());
                int bSize = (int) bufferSize.getValue();
                int iRange = Integer.parseInt(initialRange.getText());
                int fRange = Integer.parseInt(finalRange.getText());

                tasksPane.setVisible(true);
                settingsPane.setVisible(false);

                Buffer buffer = new Buffer(bSize, toDoTable, doneTable);
                Consumer.setStaticProperties(consWaitingTime, buffer);
                Producer.setStaticProperties(iRange, fRange, prodWaitingTime, buffer);

                for(int i = 1; i <= prodQty; i++) {
                    new Producer(i).start();
                }   
                for (int i = 1; i <= consQty; i++) {
                    new Consumer(i).start();
                }
                
            }else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Shiale!");
                alert.setContentText("Final range value must be greater than the initial.");
                alert.showAndWait();
            } 
        }else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Shiale!");
            alert.setContentText("Cannot leave inputs in blank.");
            alert.showAndWait();
        } 
    }       
    
}
