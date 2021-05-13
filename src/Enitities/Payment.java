/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitities;

/**
 *
 * @author acer
 */
public class Payment {
    private int cardNumber;
    private int idClient;
    private String cardName;
    
    public Payment(){}

    public Payment(int cardNumber, int idClient, String cardName) {
        this.cardNumber = cardNumber;
        this.idClient = idClient;
        this.cardName = cardName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "Payment{" + "cardNumber=" + cardNumber + ", idClient=" + idClient + ", cardName=" + cardName + '}';
    }
    
}
