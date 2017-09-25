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
public class TestLinearRate {
    //Test a single hour parking
    @Test
    public void shouldDisplay120MinFor300cent() {
        RateStrategy rs = new LinearRateStrategy();
        assertEquals(300 / 5 * 2, rs.calculateTime(300) );
    } 
    
}
