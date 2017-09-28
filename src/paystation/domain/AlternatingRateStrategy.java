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
    public AlternatingRateStrategy(RateStrategy weekendStrategy, RateStrategy weekdayStrategy) {
        this.weekendStrategy = weekendStrategy;
        this.weekdayStrategy = weekdayStrategy;
        this.currentState = null;
    }
    
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
