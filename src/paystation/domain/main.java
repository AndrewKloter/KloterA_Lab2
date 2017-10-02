/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.*;

/**
 *
 * @author tuf63516
 */



public class main {
    public PayStationFactory factory;
    
    
    public static void main(String[] args) 
            throws IllegalCoinException {

        PayStationImpl psi = new PayStationImpl();
        
        System.out.println("Hello, select your operation: "
               + "\nInsert 1 for Deposit"
               + "\nInsert 2 for Display"
               + "\nInsert 3 for Buy Ticket"
               + "\nInsert 4 for Cancel"
               + "\nInsert 5 for Change Rate Strategy");
         Scanner operation = new Scanner(System.in);
        //char choice = operation.nextLine();
         int choice = operation.nextInt();
         
         
         int a = 0;
         System.out.println("You chose: " + choice);
        
              switch(choice) {
             case 1: 
                 while (a != 1) {
                 System.out.println("Deposit your coins; 5, 10, or 25. Or enter 0 to exit.");
                 Scanner coinValue = new Scanner(System.in);
                 int value = coinValue.nextInt();
                 
                 if (value != 0) {
                     psi.addPayment(value);
                 } else {
                     a = 1;
                    }
                 }
                 break;
             case 2: ;
                 break;
             case 3: 
                 //PayStationImpl.buy();
                 break;
             case 4: ;
                 break;
             case 5: 
                 System.out.println("Which rate Strategy would you like to change to?" 
                 + "\n1 for Linear"
                 + "\n2 for Progressive"
                 + "\n3 for Alternation");
                 
                 Scanner rate = new Scanner(System.in);
                 int sRate = rate.nextInt();
                 if (sRate == 1) {
                     psi.changeRateStrategy(1);
                 } else if (sRate == 2) {
                     psi.changeRateStrategy(2);
                 } else if (sRate == 3) {
                     psi.changeRateStrategy(3);
                 }
                 break;
         }
    }
    
}
