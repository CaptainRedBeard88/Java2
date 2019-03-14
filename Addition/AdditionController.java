/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addition;

// AdditionController.java
// Controller that handles calculateButton event
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdditionController {

    @FXML
    private TextField number1TextField;

    @FXML
    private TextField number2TextField;

    @FXML
    private Label resultLabel;

    @FXML
    void addButtonPressed(ActionEvent event) {
      try {
         int number1 = Integer.parseInt(number1TextField.getText());
         int number2 = Integer.parseInt(number2TextField.getText());
         resultLabel.setText("The sum is " + (number1 + number2));
      }
      catch (NumberFormatException ex) {
         number1TextField.setText("");
         number2TextField.setText("");
      }    
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      // listener for changes to number1TextField's text
      number1TextField.textProperty().addListener(
         new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, 
               String oldValue, String newValue) {
               resultLabel.setText("");
            }
         }
      );

      // listener for changes to number2TextField's text
      number2TextField.textProperty().addListener(
         new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, 
               String oldValue, String newValue) {
               resultLabel.setText("");
            }
         }
      );
   }
}