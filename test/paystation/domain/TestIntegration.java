/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.GregorianCalendar;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
//java.io.* is for ByteArrayOutputStream baos and PrintStream
import java.io.*;


/**
 *
 * @author Andrew
 */
public class TestIntegration {
    private PayStation ps;
    
     /** return the number of lines in the given receipt */
  private int getReceiptLineCount(Receipt receipt) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream stream = new PrintStream(baos);
    receipt.print(stream);
    String output = baos.toString();
    String[] lines = output.split("\n");
    return lines.length;
  }
  
  private void addOneDollar() throws IllegalCoinException {
     ps.addPayment(25); ps.addPayment(25); ps.addPayment(25); ps.addPayment(25);
 }
    
    @Test
    public void shouldIntegrateLinearRateCorrectly() 
            throws IllegalCoinException {
        ps = new PayStationImpl(new AlphaTownFactory() );
        addOneDollar(); addOneDollar();
        
        int minutes = 200/5*2;
        Calendar now = GregorianCalendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        int result = now.get(Calendar.HOUR_OF_DAY) * 100 
                    + 
                     now.get(Calendar.MINUTE);
    
    assertEquals( "Linear Rate: 2$ should give 80 min => end time "+result,
                  result , ps.readDisplay() );

    Receipt receipt = ps.buy();
    // test that a standard receipt is issued.
    assertEquals( "AlphaTown should use standard receipts",
                  5, getReceiptLineCount(receipt) );
  }
    
    
    @Test 
  public void shouldIntegrateBetaTownRateCorrectly() 
    throws IllegalCoinException {
    ps = new PayStationImpl( new BetaTownFactory() );
    //$2.0: 1.5 gives 1 hour, next 0.5 gives 15 minutes.
    addOneDollar(); addOneDollar();
    
    assertEquals( "Progressive Rate: 2$ should give 75 min ",
                  75 , ps.readDisplay() );

    Receipt receipt = ps.buy();
    // test that a barcode receipt is issued.
    assertEquals( "BetaTown should use barcode receipts",
                  6, getReceiptLineCount(receipt) );
  }
  
  
  @Test 
  public void shouldIntegrateGammaTownRateCorrectly() 
    throws IllegalCoinException {
    ps = new PayStationImpl( new GammaTownFactory() );
    addOneDollar(); addOneDollar();
    
    assertEquals( "WILL FAIL DURING WEEKENDS: Linear rate 2$ = 80 min ",
                  80 , ps.readDisplay() );
    
    Receipt receipt = ps.buy();
    //test that a standard receipt is issued.
    assertEquals( "GammaTown should use standard receipts",
                  5, getReceiptLineCount(receipt) );
  }
}
