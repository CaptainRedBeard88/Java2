package tipcalculator2;
/*
Adriano Nannini 3/7/19
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculator2Controller { 
   // formatters for currency and percentages
   private static final NumberFormat CURRENCY = 
      NumberFormat.getCurrencyInstance();
   private static final NumberFormat PERCENT = 
      NumberFormat.getPercentInstance();
   
   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML 
   private TextField amountTextField; 

   @FXML
   private Label tipPercentageLabel; 

   @FXML
   private Slider tipPercentageSlider;

   @FXML
   private TextField tipTextField;

   @FXML
   private TextField totalTextField;


    private void calculate(){
     try {
         BigDecimal amount = new BigDecimal(amountTextField.getText());
         BigDecimal tip = amount.multiply(tipPercentage);
         BigDecimal total = amount.add(tip);      
         tipTextField.setText(CURRENCY.format(tip));
         totalTextField.setText(CURRENCY.format(total));
         
      }
      catch (NumberFormatException ex) {
         amountTextField.setText("Enter amount");
         amountTextField.selectAll();
         amountTextField.requestFocus();
      }
    }
    
    
   // called by FXMLLoader to initialize the controller
   public void initialize() {
      // 0-4 rounds down, 5-9 rounds up 
      CURRENCY.setRoundingMode(RoundingMode.HALF_UP);
      
      amountTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          calculate();
      });
      
      // listener for changes to tipPercentageSlider's value
      tipPercentageSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
          tipPercentage = 
                  BigDecimal.valueOf(newValue.intValue() / 100.0);
          tipPercentageLabel.setText(PERCENT.format(tipPercentage));
          calculate();
      });
   }
   

}