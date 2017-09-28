/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author Andrew
 */

public class ProgressiveRateStrategy implements RateStrategy {
    @Override
    public int calculateTime(int amount) {
        int time = 0;
        //System.out.println(amount);
        //amount = amount;    //Must do this because in PayStationImpl I do insertedSoFar / 5 * 2. So this counteracts that.
        if (amount >= 950) { // beyond 4  hours
            amount -= 950;
            time = (60 * 4) /*minutes*/ + amount * 3 / 10;
        } else if (amount >= 650) { // from third to 4th hour
            amount -= 650;
            time = (60 * 3) /*minutes*/ + amount * 3 / 10;
        } else if (amount >= 350) { // from 2nd to third hour
            amount -= 350;
            time = (60 * 2) /*minutes*/ + amount * 3 / 10;
        } else if (amount >= 150) { // from 1st to 2nd hour
            amount -= 150;
            time = 60 /*minutes*/ + amount * 3 / 10;
        } else { // up to 1st hour
            time = amount;
        }
        return time;
    }
    
}

