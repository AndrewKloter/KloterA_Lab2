/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author tuf63516
 */
public interface PayStationFactory {
    
    //Create an instance of the rate strategy to use
    public RateStrategy createRateStrategy();
    
    //Create an instance of the receipt
    public Receipt createReceipt(int parkingTime);
    
    //Create an instance of DisplayStrategy
    public DisplayStrategy createDisplayStrategy();
}
