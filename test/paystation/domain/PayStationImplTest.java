/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

//import java.util.*;
//import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.*;



public class PayStationImplTest {

    PayStation ps;
  

    @Before
    public void setup() {
    RateStrategy rs = new AlternatingRateStrategy(new LinearRateStrategy(), 
                                                  new ProgressiveRateStrategy() );
    ps = new PayStationImpl(rs);
    //ps = new PayStationImpl(new LinearRateStrategy() );
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }
    
    
    @Test
    public void returnTotalAmountEntered()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(5);
        assertEquals(15, ps.empty());
    }
    
    @Test 
    public void canceledEntryDoesNotAddAmountReturnedByEmpty()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        ps.addPayment(5);
        assertEquals(5, ps.empty());
    }
    
    
    @Test
    public void callToEmptyResetTotalToZero()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(5);
        ps.empty();
        assertEquals(0, ps.empty());
    }
    
    
    @Test
    public void callToCancelReturnsMapContainingOneCoinEntered()
            throws IllegalCoinException {
        Map<Integer, Integer> map = ((PayStationImpl)ps).map;
        ps.addPayment(5);  
        //ps.addPayment(25);  
        ps.cancel();
      //  System.out.println(((PayStationImpl)ps).map);
       
        //assertEquals(1, (int)map.getOrDefault(5,0));   
        //assertEquals(1, (int)map.getOrDefault(25,0));
        assertEquals(1, (int)map.get(5));

    }
    
    
    
    @Test
        public void callToCancelReturnsMapContainingMixtureCoinsEntered()
            throws IllegalCoinException {
        Map<Integer, Integer> map = ((PayStationImpl)ps).map; 
        
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(5);
        ps.cancel();
        assertEquals(3, (int)map.get(25));
        assertEquals(2, (int)map.get(10));
        assertEquals(1, (int)map.get(5));
    }
        
        
        @Test
        public void callToCancelReturnsMapNotContainingKeyForCoinNotEntered()
            throws IllegalCoinException {
        Map<Integer, Integer> map = ((PayStationImpl)ps).map; 
        
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(10);
        ps.cancel();
        //if(map.get(5) != null);
        assertEquals(2, (int)map.get(25));
        assertEquals(1, (int)map.get(10));
        //assertEquals(0, (int)map.get(5));
    }
        
        
        //When the map is cleared, all values in it will be null, 
            //so when testing, we expect to get a null pointer exception.
        @Test(expected=NullPointerException.class)
        public void callToCancelClearsTheMap()
                throws IllegalCoinException {
        Map<Integer, Integer> newMap = ((PayStationImpl)ps).newMap; 

            ps.addPayment(25);
            ps.cancel();
            assertEquals(0, (int)newMap.get(25));
        }
        
        
        //When the map is cleared, all values in it will be null, 
            //so when testing, we expect to get a null pointer exception.
        @Test(expected=NullPointerException.class)
        public void callToBuyClearsTheMap()
                throws IllegalCoinException {
        Map<Integer, Integer> map = ((PayStationImpl)ps).map; 

            ps.addPayment(10);
            ps.buy();
            assertEquals(0, (int)map.get(10));                    
        }
        
        
        
        
        
        @Test
        public void shouldIntegrateProgressiveRateCorrectly() 
                throws IllegalCoinException {
            ps = new PayStationImpl(new ProgressiveRateStrategy() );
            addOneDollar(); addOneDollar();
            
            assertEquals("Progressive rate: 2$ should give 75 min ", 75 , ps.readDisplay() );
        }
        
         private void addHalfDollar() throws IllegalCoinException {
        ps.addPayment(25); 
        ps.addPayment(25);
    }
    
    private void addOneDollar() throws IllegalCoinException {
        addHalfDollar(); 
        addHalfDollar();    
    }

    /*
    @Test
    public void shouldAcceptLegalCoins()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        
        assertEquals("Should accept 5, 10, and 25 cents", 5+10+25, ps.readDisplay() );
    }
*/
        
       
       

}

