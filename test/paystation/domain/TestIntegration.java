/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;


/**
 *
 * @author Andrew
 */
public class TestIntegration {
    private PayStation ps;
    
    
    @Test
    public void shouldIntegrateLinearRateCorrectly() 
            throws IllegalCoinException {
        ps = new PayStationImpl(new LinearRateStrategy() );
        addOneDollar(); addOneDollar();
        
       assertEquals("LInear rate: 2$ should give 80 min ", 80 , ps.readDisplay() );
    }
    
    
    @Test
 public void shouldIntegrateProgressiveRateCorrectly() 
            throws IllegalCoinException {
        ps = new PayStationImpl(new ProgressiveRateStrategy() );
        addOneDollar(); addOneDollar();
        
       assertEquals("Progressive rate: 2$ should give 75 min ", 75 , ps.readDisplay() );
    }
 
 
 private void addOneDollar() throws IllegalCoinException {
     ps.addPayment(25); ps.addPayment(25); ps.addPayment(25); ps.addPayment(25);
 }
}
