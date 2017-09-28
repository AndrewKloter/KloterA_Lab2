/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.*;

/**
 *
 * @author tuf63516
 */
public class TimeDisplayStrategy implements DisplayStrategy {
    public int calculateOutput(int minutes) {
        Calendar now = GregorianCalendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        int result = now.get(Calendar.HOUR_OF_DAY) * 100
                + now.get(Calendar.MINUTE);
        return result;
    }
    
}
