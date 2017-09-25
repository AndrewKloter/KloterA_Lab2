/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author Andrew
 */

//One cent = one minute
public class One2OneRateStrategy implements RateStrategy {
    @Override
    public int calculateTime(int amount) {
        return amount;
    }
    
}
