/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.*;


public class PayStationImplProgTest {
    
    PayStation ps;
    RateStrategy rs;
    
    @Before
    public void setUp() {
        ps = new PayStationImpl(new ProgressiveRateStrategy() );
        rs = new ProgressiveRateStrategy();
    }
   
    @Test
    public void shouldGive60MinFor150cent() {
        assertEquals(60 /*minutes*/ , rs.calculateTime(150) );
    }
    
    @Test
    public void shouldGive120MinFor350cent() {
        //Two hours: $1.5 + $2.0
        assertEquals(2 * 60 /*minutes*/ , rs.calculateTime(350) );
    }
    
    
    @Test
    public void shouldIntegrateProgressiveRateCorrectly()
            throws IllegalCoinException {
        ps = new PayStationImpl(new ProgressiveRateStrategy() );
        
        addOneDollar(); addOneDollar();
        
        assertEquals( "Progressive Rate: 2$ should give 75 min ", 75, ps.readDisplay() );
    }
    
    
    
    
    private void addHalfDollar() throws IllegalCoinException {
        ps.addPayment(25); 
        ps.addPayment(25);
    }
    
    private void addOneDollar() throws IllegalCoinException {
        addHalfDollar(); 
        addHalfDollar();    
    }
    
   
    //Old code before i changed public void setUp part. Book pg 139.
    /* 
    @Test
    public void shouldDisplay60MinFor150Cent()
            throws IllegalCoinException {
        //first hour: $1.5
        addOneDollar();
        addHalfDollar();
               
        assertEquals(60 /*minutes, (first hour), ps.readDisplay() );
    }
    
    //Testing ability to calculate second hour pricing.
    @Test 
    public void shouldDisplay120MinutesFor350Cent()
            throws IllegalCoinException {
        //Two hours: $1.5 + $2.0
        addOneDollar();
        addOneDollar();
        addOneDollar();
        addHalfDollar();
        
        assertEquals(2 * 60 /*minutes, (2 hours), ps.readDisplay() );
    }*/
    
}
