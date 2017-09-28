/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

//import java.util.Calendar;
import java.util.*;

/**
 *
 * @author tuf63516
 */


public class AlternatingRateStrategy implements RateStrategy {
    private RateStrategy weekendStrategy, weekdayStrategy, currentState;
    //Below, the weekdayStrategy refers to the first argument passed into 
        //AlternatingRateStrategy in the PayStationImplTest, which is LinearRateStrategy.
    //And the weekendStrategy refers to the second argument passed into 
        //AlternatingRateStrategy in the PayStationImplTest, which is ProgressiveRateStrategy.
    public AlternatingRateStrategy(RateStrategy weekdayStrategy, RateStrategy weekendStrategy) {
        this.weekdayStrategy = weekdayStrategy;
        this.weekendStrategy = weekendStrategy;
        this.currentState = null;
    }
    
    @Override
    public int calculateTime(int amount) {
        if (isWeekend() ) {
            currentState = weekendStrategy;
        } else { 
            currentState = weekdayStrategy;
        }
        return currentState.calculateTime(amount);
    }
    
    private boolean isWeekend() {
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY
                ||
                dayOfWeek == Calendar.SUNDAY);
    }
    
}
