package org.ironstudios.userssvc.validator;

import java.util.regex.Pattern;

/**
 * @author arjun
 * @version 1.0
 */
public class MonetaryValidationUtil {

    /**
     * validates the positive amount for an expense
     * @param amount
     * @return is amount valid
     */
    public static boolean isValidAmount(String amount){
        String regex = "^[1-9][0-9]*$";
        if(amount==null || amount.isBlank()){
            return false;
        }
        return Pattern.matches(regex,amount);
    }
}
