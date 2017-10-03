/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author tuf63516
 */



public class main {
    
    public static void menu() {
    System.out.println("\nHello, select your operation, or 0 to exit: "
               + "\nInsert 1 for Deposit"
               + "\nInsert 2 for Display"
               + "\nInsert 3 for Buy Ticket"
               + "\nInsert 4 for Cancel"
               + "\nInsert 5 for Change Rate Strategy");    
    }
    
    public static void main(String[] args) 
            throws IllegalCoinException {

        int a = 0;
        PayStationImpl psi = new PayStationImpl();
        //StandardReceipt sr = new StandardReceipt();
        Scanner scan = new Scanner(System.in);
        int choice;
        
        while (a == 0) {
         menu();
         choice = scan.nextInt();
         scan.nextLine();
     //    System.out.println("You chose: " + choice);
              switch(choice) {
             case 0: //Exit the menu/program
                 a = 1;
                 break;
                 
             case 1: //Deposit coins
                 int value;
                 while (a != 1) {
                 System.out.println("Deposit your coins; 5, 10, or 25. Or enter 0 to exit.");
                 //Scanner coinValue = new Scanner(System.in);
                 value = scan.nextInt();
                 scan.nextLine();
                 
                 if (value != 0) {
                     psi.addPayment(value);
                 } else {
                     a = 1;
                    }
                 }
                 a = 0;
                 break;
                 
             case 2: //Display
                 int timeBought;
                 timeBought = psi.readDisplay();
                 System.out.println("You have bought " + timeBought + " minutes.");
                 break;
                 
             case 3: 
                 //ORIGINAL:
                //Receipt r;
                //r = psi.buy();
                //System.out.println("receipt: " + r.value());
                 
             
 
                 Receipt r;
                 
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 PrintStream ps = new PrintStream(baos);
                 //PrintStream stream;
                 r = psi.buy();
                 System.out.println("receipt: " + r.value());
                 //System.out.println("receipt: " + r.print(ps));   
                 r.print(ps);
                 String output = baos.toString();
                 String[] lines = output.split("\n");
                 //sr.print(stream);
                 //System.out.println("Your receipt: " + StandardReceipt.print(stream));
                 break;
                 
             case 4: //Cancel, print total coin values returned and number of each coin type
                 HashMap m;
                 m = psi.cancel();
                 
                 Integer five = (Integer)m.get(5);
                 //System.out.println(five);
                 Integer ten = (Integer)m.get(10);
                 //System.out.println(ten);
                 Integer twentyFive = (Integer)m.get(25);
                 //System.out.println(twentyFive);
                 int totalChange = (5*five)+(10*ten)+(25*twentyFive);
                 
                 System.out.println("Your total change: " + totalChange + 
                         "\nNickels: " + five +
                         "\nDimes: " + ten +
                         "\nQuarters: " + twentyFive);
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
    
}
