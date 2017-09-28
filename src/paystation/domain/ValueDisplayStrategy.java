/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author tuf63516
 */
public class ValueDisplayStrategy implements DisplayStrategy {
    @Override
    public int calculateOutput(int minutes) {
        return minutes;
    }
    
}
