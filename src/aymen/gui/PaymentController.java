package aymen.gui;

import com.stripe.model.Customer;
import Service.PaymentCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class PaymentController implements Initializable {

    @FXML
    private TextField TXCARDINFO;
    @FXML
    private Button BUY;
    @FXML
    private TextField exp_month;
    @FXML
    private TextField cvv;
    @FXML
    private TextField exp_year;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("1");
        //Integer value1, value2, value3,value4;
        System.out.println("1");
        
        BUY.setOnAction(e -> {
//           Integer value2 = Integer.valueOf(exp_month.getText());
//           Integer value3 = Integer.valueOf(exp_year.getText());
//           Integer value4 = Integer.valueOf(cvv.getText());
            if (TXCARDINFO.getText().isEmpty()||
                exp_month.getText().isEmpty()||
                exp_year.getText().isEmpty()||
                cvv.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("make sure no fields are empty");
                alert.showAndWait();
            } else {
//                    Integer value1 = Integer.valueOf(TXCARDINFO.getText());
                if (TXCARDINFO.getText()=="4242424242424242") {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter A Valid Card Number");
                    alert.showAndWait();
                }
                else{
                    Integer value2 = Integer.valueOf(exp_month.getText());
                    if(value2>12&&value2<1)
                    {   
                        alert();
                        System.out.println("2");
                    }
                    else{
                        Integer value3 = Integer.valueOf(exp_year.getText());
                        if(value3<2020)
                        {
                            alert();
                            System.out.println("3");}
                        else
                        {
                            Integer value4 = Integer.valueOf(cvv.getText());
                            if(value4<99&&value4>999)
                            {
                                alert();
                                System.out.println("4");
                            }
                            else{
                                PaymentCrud pay=new PaymentCrud();
                                //pay.createStripCustomer();
                                
                                pay.addStripeCard(pay.createStripCustomer(),TXCARDINFO.getText(),exp_month.getText() ,exp_year.getText(), cvv.getText());
//                                pay.chargeStripCard();
                                
                            }
                        }
                    }
                }
            }

        });
    }
    
    public void alert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("WARNING");
                        alert.setHeaderText(null);
                        alert.setContentText("Enter A Valid Input");
                        alert.showAndWait();
    }
    public  boolean validitychk(long cnumber) {
      return (thesize(cnumber) >= 13 && thesize(cnumber) <= 16) && (prefixmatch(cnumber, 4)
         || prefixmatch(cnumber, 5) || prefixmatch(cnumber, 37) || prefixmatch(cnumber, 6))
         && ((sumdoubleeven(cnumber) + sumodd(cnumber)) % 10 == 0);
   }
   // Get the result from Step 2
   public  int sumdoubleeven(long cnumber) {
      int sum = 0;
      String num = cnumber + "";
      for (int i = thesize(cnumber) - 2; i >= 0; i -= 2)
         sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
      return sum;
   }
   // Return this cnumber if it is a single digit, otherwise,
   // return the sum of the two digits
   public static int getDigit(int cnumber) {
      if (cnumber < 9)
         return cnumber;
      return cnumber / 10 + cnumber % 10;
   }
   // Return sum of odd-place digits in cnumber
   public  int sumodd(long cnumber) {
      int sum = 0;
      String num = cnumber + "";
      for (int i = thesize(cnumber) - 1; i >= 0; i -= 2)
         sum += Integer.parseInt(num.charAt(i) + "");
      return sum;
   }
   // Return true if the digit d is a prefix for cnumber
   public  boolean prefixmatch(long cnumber, int d) {
      return getprefx(cnumber, thesize(d)) == d;
   }
   // Return the number of digits in d
   public  int thesize(long d) {
      String num = d + "";
      return num.length();
   }
   // Return the first k number of digits from
   // number. If the number of digits in number
   // is less than k, return number.
   public long getprefx(long cnumber, int k) {
      if (thesize(cnumber) > k) {
         String num = cnumber + "";
         return Long.parseLong(num.substring(0, k));
      }
      return cnumber;
   }

}
