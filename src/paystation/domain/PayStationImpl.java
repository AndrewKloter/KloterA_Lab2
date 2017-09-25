package paystation.domain;

import java.util.*;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private RateStrategy rateStrategy;
    public HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>();
    
    //Have to change the constructor to implement rateStrategy.
     public PayStationImpl( RateStrategy rateStrategy) {
        this.rateStrategy = rateStrategy;
    } 

    

  

  

     /*
    public PayStationImpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: break;
            case 10: break;
            case 25: break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = rateStrategy.calculateTime(insertedSoFar);
        //map.put(coinValue, map.getOrDefault(coinValue, 0)+1); 
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        map.clear();
        return r;
        
    }

    @Override
    public Map<Integer, Integer> cancel() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() {}; 
        HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>() {}; 

        int key;
        int value;
        int amount = insertedSoFar;
        
        if ((amount % 25) == 0) {
            key = 25;
            value = 1;
            //amount = (amount / 25);
        } else if ((amount % 10) == 0) {
            key = 10;
            value = 1;
            //amount = (amount / 10);
        } else {
            key = 5;
            value = 1;
        }
        map.put(key,value);
        //map.get(5);
        reset();
        newMap = map;
        map.clear();
        return newMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
    }
    
    @Override
    public int empty() {
        int total = 0;
        total = insertedSoFar;
        reset();
        return total;
    }
}
    
    /*
     public static void main(String[] args) throws IllegalCoinException {
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
        System.out.println("You chose: " + choice);
        
        //int choiceString;
        switch (choice) {
            case 1: 
                System.out.println("Deposit your coins.");
                Scanner coinValue = new Scanner(System.in);
                int value = coinValue.nextInt();
                psi.addPayment(value);
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
    }
    
}
*/

