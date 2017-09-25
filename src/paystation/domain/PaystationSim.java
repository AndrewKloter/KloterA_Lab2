/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.*;
/**
 *
 * @author Andrew
 */
public class PaystationSim {
   // PayStationImpl paystationimpl;
    //paystationimpl =  new PayStationImpl();
    

   
    
    
    
    public static void main(String[] args) throws IllegalCoinException {

        //int coinValue = 5;
         //PayStationImpl test1 = new PayStationImpl();
    //int attempt = test1.addPayment(coinValue);
        
        
         //PayStationImpl addPayment = new PayStationImpl();

        //Get error that must initialize the rs variable for buyticket function, so just initialize by null.
        RateStrategy rs = null;
        
        PayStationImpl buyticket = new PayStationImpl(rs);
        
        
        System.out.println("Hello, select your operation: "
                + "\nInsert 1 for Deposit"
                + "\nInsert 2 for Display"
                + "\nInsert 3 for Buy Ticket"
                + "\nInsert 4 for Cancel"
                + "\nInsert 5 for Change Rate Strategy");
        Scanner operation = new Scanner(System.in);
        //char choice = operation.nextLine();
        int choice = operation.nextInt();
        System.out.println("You chose: " + choice);
        
        //int choiceString;
       
        switch (choice) {
            case 1: 
               // System.out.println("Deposit your coins.");
                //Scanner cValue = new Scanner(System.in);
                //int value = cValue.nextInt();
                //test1.addPayment(value);
                break;
            case 2: ;
                break;
            case 3: 
                ;
                break;
            case 4: ;
                break;
            case 5: ;
                break;
        }
        
        
        
        
        
        
        /*
        System.out.println("Hello, select your operation: "
                + "\nInsert 1 for Deposit"
                + "\nInsert 2 for Display"
                + "\nInsert 3 for Buy Ticket"
                + "\nInsert 4 for Cancel"
                + "\nInsert 5 for Change Rate Strategy");
        Scanner operation = new Scanner(System.in);
        //char choice = operation.nextLine();
        int choice = operation.nextInt();
        System.out.println("You chose: " + choice);
        
        //int choiceString;
        switch (choice) {
            case 1: 
                System.out.println("Deposit your coins.");
                int value = 5;
                //paystationimpl.addPayment(value);
                break;
            case 2: ;
                break;
            case 3: ;
                break;
            case 4: ;
                break;
            case 5: ;
                break;
        }
        */
    }

    
}
