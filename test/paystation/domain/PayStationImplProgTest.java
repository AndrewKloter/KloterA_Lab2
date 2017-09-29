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
    
    //PayStation ps;
    RateStrategy rs;
    
    @Before
    public void setUp() {
        //ps = new PayStationImpl(new ProgressiveRateStrategy() );
        rs = new ProgressiveRateStrategy();
    }
   
    @Test
    public void shouldGive60MinFor150cent() 
        throws IllegalCoinException {
        //These dollar methods are no longer needed since we can directly use 
            //rs.calculateTime() now since we have an instance of the RateStrategy rs now.
            //We now use rs.calculateTime() instead of ps.readDisplay in the assertEquals().
        //addOneDollar(); addHalfDollar();
        assertEquals(60 /*minutes*/ , rs.calculateTime(150) );
    }
        
    
    @Test
    public void shouldGive120MinFor350cent() 
            throws IllegalCoinException {
        //Two hours: $1.5 + $2.0
        //These dollar methods are no longer needed since we can directly use 
            //rs.calculateTime() now since we have an instance of the RateStrategy rs now.
            //addOneDollar(); addOneDollar(); addOneDollar(); addHalfDollar();
        assertEquals(2 * 60 /*minutes*/ , rs.calculateTime(350) );
    }
    
       @Test
    public void shouldGive180MinFor650cent() 
            throws IllegalCoinException {
        //Three hours: $1.5 + $2.0 + $3.0
        assertEquals(3 * 60 /*minutes*/ , rs.calculateTime(650) );
    }
    
      @Test
    public void shouldGive240MinFor950cent() 
            throws IllegalCoinException {
        assertEquals(4 * 60 /*minutes*/ , rs.calculateTime(950) );
    } 
}
