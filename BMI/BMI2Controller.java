/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmi2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BMI2Controller {

    
    double weight;
    double height;
    double bmi;
    
    @FXML
    private TextField number1TextField;

    @FXML
    private TextField number2TextField;

    @FXML
    private Label resultLabel;

    @FXML
    void metricButtonPressed(ActionEvent event) {
        checkValidData();
        resultLabel.setText(displayResult(calculateBMIMetric(weight, height)));
    }

    @FXML
    void usButtonPressed(ActionEvent event) {
        checkValidData();
        resultLabel.setText(displayResult(calculateBMIUS(weight, height)));
    }
    
    private void checkValidWeight(){
         try{
          weight = Double.parseDouble(number1TextField.getText());
        }
        catch(NumberFormatException ex){
         number1TextField.setText("Enter a height");
        }
    }
    private void checkValidHeight(){
        try{
        height = Double.parseDouble(number2TextField.getText());
        }
        catch(NumberFormatException ex) {
                 number2TextField.setText("Enter a weight");
        }
    }
    
    private void checkZero(){
          if(weight == 0) number1TextField.setText("Weight must be more than 0");
          if(height == 0) number2TextField.setText("Height must be more than 0");
    }
    
    private double calculateBMIMetric(double weight, double height){ 
        double totalWeight; 
        totalWeight = weight / (height * height);
        return  totalWeight;
    }
    
    private double calculateBMIUS(double weight, double height){   
              return (weight * 703)/(height * height);          
    }
    
    private String displayResult(double bmi){
        String result = "Invalid BMI";
        if(bmi <= 0){
            result = "BMI must be above 0";
            return result;
        }
        if(bmi <= 18.5){
            result = String.format("With a BMI of %.1f you are underweight", bmi);
            return result;
        }
        else if(bmi <= 24.9){
            result = String.format("With a BMI of %.1f you have a normal weight", bmi);
            return result;
        }
        else if(bmi <= 29.9){
            result = String.format("With a BMI of %.1f you are overweight", bmi);
            return result;
        }        
        else if(bmi < 150){
            result = String.format("With a BMI of %.1f you are obese", bmi);
            return result;
        } 
        else return result;
    }
        private void checkValidData(){
        checkValidWeight();
        checkValidHeight();
        checkZero();
        }
        
    
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
