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
    //private PayStation psLinear, psProgressive;
    
    //Need this because no default constructor on PayStaionImpl (super class for PayStationAlternatingRate.
        //So, have to call super() at the first position in my subclass (PayStaionAlternatingRate) constructor.
     PayStationAlternatingRate(RateStrategy rs) {
        super(rs);
    }
     
    //Here we are instantiating (creating an instance of)/creating an object of the LinearRateStrategy class.
    LinearRateStrategy LRStrategy = new LinearRateStrategy();
    ProgressiveRateStrategy PRStrategy = new ProgressiveRateStrategy();
    
    private boolean isWeekend() {
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY
                ||
                dayOfWeek == Calendar.SUNDAY);
    }
    
    @Override
    protected int calculateTime(int amount) {
        int time;
        if (isWeekend() ) {
            time = LRStrategy.calculateTime(amount);
        } else {
            time = PRStrategy.calculateTime(amount);
        }
        return time;
    }
    

    
    
}
