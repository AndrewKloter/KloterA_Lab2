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
class BetaTownFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new ProgressiveRateStrategy();
    }
    
    @Override
    public Receipt createReceipt(int parkingTime) {
        return new StandardReceipt(parkingTime, true);
    }
    
    @Override
    public DisplayStrategy createDisplayStrategy() {
        return new ValueDisplayStrategy(); 
    }
    
}
