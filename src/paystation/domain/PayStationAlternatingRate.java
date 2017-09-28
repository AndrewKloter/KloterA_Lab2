/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author tuf63516
 */

public class PayStationAlternatingRate extends PayStationImpl {
    private PayStation psLinear, psProgressive;
    
    
    
    
    @Override
    protected int calculateTime(int amount) {
        int time;
        if (isWeekend() ) {
            time = psProgressive.calculateTime(amount);
        } else {
            time = psLinear.calculateTime(amount);
        }
        return time;
    }
    

    
    
}
