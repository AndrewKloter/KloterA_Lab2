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
    private boolean isWeekend() {
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return ( dayOfWeek == Calendar.SATURDAY
                ||
                dayOfWeek == Calendar.SUNDAY);
    }
    
    //@Override
    protected int calculateTime(int amount) {
        int time;
        if (isWeekend() ) {
        //System.out.println(amount);
        //amount = amount;    //Must do this because in PayStationImpl I do insertedSoFar / 5 * 2. So this counteracts that.
        time = super.calculateProgressiveTime(amount);
        } else { // up to 1st hour
            time = super.calculateLinearTime(amount);
        }
        return time;
      
    }
    
    
}
