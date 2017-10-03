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
    
    private HashMap map = new HashMap();
    
    //public HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    //public HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>();

    private RateStrategy rateStrategy = new LinearRateStrategy();
    
    //private DisplayStrategy displayStrategy;
    
    //private PayStationFactory factory;
    
    /* 
    public PayStationImpl(PayStationFactory factory) {
        this.factory = factory;
        this.rateStrategy = factory.createRateStrategy();
        this.displayStrategy = factory.createDisplayStrategy();
        reset();
    }
    
    public PayStationImpl(RateStrategy rateStrategy) {
        this.rateStrategy = rateStrategy;
    }
    */
    
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch(coinValue) {
            case 5: break;
            case 10: break;
            case 25: break;
            default: 
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        Integer c = coinValue;
        Integer n = 1;
        if (map.containsKey(c)) {
            n = (Integer)map.get(c);
            n++;
        }
        map.put(c, n);
        insertedSoFar += coinValue;
        timeBought = rateStrategy.calculateTime(insertedSoFar);
        //timeBought = insertedSoFar / 5 * 2;
    }
    
    @Override
    public int readDisplay() {
        //return displayStrategy.calculateOutput(timeBought);
        return timeBought;
    }
    
    @Override
    public Receipt buy() {
        Receipt r = new StandardReceipt(timeBought);
        //timeBought = insertedSoFar = 0;
        //reset();
        return r;
        //return new ReceiptImpl(timeBought);
    }
    
    @Override
    public HashMap<Integer, Integer> cancel() {
        HashMap m = (HashMap)map.clone();
        m.putAll(map);
        reset();
        return m;
        //timeBought = insertedSoFar = 0;
    }
    

    private void reset() {
        timeBought = insertedSoFar = 0;
        map.clear();
    }
    
    //@Override
    protected int calculateTime(int paidSoFar) {
        return paidSoFar * 2 / 5;
    }
    
    @Override
    public int empty() {
        int total = 0;
        total = insertedSoFar;
        reset();
        return total;
    }
    
    
    public void changeRateStrategy(int rs) {
        if (rs == 1) 
            rateStrategy = new LinearRateStrategy(); 
        if (rs == 2) 
            rateStrategy = new ProgressiveRateStrategy();
        if (rs == 3)
            rateStrategy = new AlternatingRateStrategy();
        
    }
  }


