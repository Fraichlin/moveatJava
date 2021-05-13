/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.param.CustomerRetrieveParams;
import Enitities.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.MyConnexion;

/**
 *
 * @author acer
 */
public class PaymentCrud {
    Connection cnx;
    PreparedStatement pr;
    
    public PaymentCrud(){
         cnx = MyConnexion.getInstance().getConnection();
    }
    
    public void addPaymentInfo(Payment pay){
        try{
           String requet="INSERT INTO payment(cardNumber,cardName,idClient) VALUES(?,?,?)";
           
           pr=cnx.prepareStatement(requet);
           
           pr.setInt(1, pay.getCardNumber());
           pr.setString(2,pay.getCardName());
           pr.setInt(3,pay.getIdClient());
           
           pr.executeUpdate();
            System.out.println("info inserted");
        }
        catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
    
    
    public void modifyPaymentInfo(Payment pay){
        try{
           String requet="UPDATE `payment` SET cardNumber=?,cardName=? WHERE idClient=?";
           
           pr=cnx.prepareStatement(requet);
           
           pr.setInt(1, pay.getCardNumber());
           pr.setString(2,pay.getCardName());
           pr.setInt(3, pay.getIdClient());
           
           pr.executeUpdate();
            System.out.println("info updated");
        }
        catch(Exception e){
            System.out.println("prob in updating info");
        }
        
    }
    
    public Boolean deletePaymentInfo(int idClient){
        try{
            String requet="delete from `payment` where idClient=?";
            pr=cnx.prepareStatement(requet);
            
            pr.setInt(1, idClient);
            
            pr.executeUpdate();
            System.out.println("PaymentInfo deleted :) ");
            return true;
        }
        catch(Exception e){
            System.out.println("problem deleting PaymentInfo");
            return false;
        }
    }
    
    
    
    //CreateStripCustmor
    public  String createStripCustomer(){
        String cid="";
        try {
            Stripe.apiKey =
            "sk_test_51IS3xuEW0KlR6HuIVQ94DfBr8PBsxzflcoIcEarxDhZmKhK9hDrv8xvbgnZ9iQ4QQuTOIc4UPq8pDgpJ93Yhr3A800rTng9H8I";
//            RequestOptions requestOptions = RequestOptions.builder()
//            .setApiKey("ssk_test_51HyOw3GGT9dICYbuMisMVBamAlnMI5GaioB0bLrtFBHl1U5nz75fnkkaxneFHn5eWB3f7muBIFM6he1Tp8pi6MCf00XDL9N8oR")
//            .build();
            Map<String, Object> customerParametre = new HashMap<String,Object>();
            customerParametre.put("email","customerB@gmail.com");
            Customer newCustomer=Customer.create(customerParametre);
            System.out.println(newCustomer.getId());
            cid= newCustomer.getId();
        } catch (StripeException ex) {
            Logger.getLogger(PaymentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cid;
    }
    
    //retrivestripecustomer
    public  void retriveStripeCustomer(String id){
        try {
            Customer c=Customer.retrieve(id);
            Gson gson= new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(c));
            } catch (StripeException ex) {
            Logger.getLogger(PaymentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //addStripeCard
    public  void addStripeCard(String cid,String c,String m,String y,String v){
        try {
            Map<String, Object> cardParam = new HashMap<String,Object>();
            cardParam.put("number",c);
            cardParam.put("exp_month",m);
            cardParam.put("exp_year",y);
            cardParam.put("cvc",v);
            
            Map<String, Object> tokenParam = new HashMap<String,Object>();
            tokenParam.put("card",cardParam);
            Token token=Token.create(tokenParam);
            
            Map<String, Object> source = new HashMap<String,Object>();
            source.put("source",token.getId());
            CustomerRetrieveParams retrieveParams =
				  CustomerRetrieveParams.builder()
				    .addExpand("sources")
				    .build();
            Customer cus=Customer.retrieve(cid, retrieveParams, null);
            cus.getSources().create(source);
            
        } catch (StripeException ex) {
            Logger.getLogger(PaymentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //chargeStripCard
    public  void chargeStripCard(){
        
        try {
//            Customer c=Customer.retrieve("cus_IZZlIIQDP0V41A");
            Customer c=Customer.retrieve("cus_JMLw7LSejouc3q");
            Map<String, Object> chargeParam = new HashMap<String,Object>();
            chargeParam.put("amount",500);
            chargeParam.put("currency","usd");
            chargeParam.put("customer", c.getId());
        } catch (StripeException ex) {
            Logger.getLogger(PaymentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

    

