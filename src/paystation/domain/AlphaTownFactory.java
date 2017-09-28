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
class AlphaTownFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new LinearRateStrategy();
    }
    @Override
    public Receipt createReceipt(int parkingTime) {
        return new StandardReceipt(parkingTime);
    }
    @Override
    public DisplayStrategy createDisplayStrategy() {
        return new TimeDisplayStrategy();
    }
}
