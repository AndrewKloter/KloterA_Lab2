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
public class BetaTownFactory implements PayStationFactory {
    public RateStrategy createRateStrategy() {
        return new ProgressiveRateStrategy();
    }
    public Receipt createReceipt(int parkingTime) {
        return new StandardReceipt(parkingTime);
    }
    
}
