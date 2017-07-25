package com.wally.pocket.util;

import android.util.Log;

import java.util.Locale;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class NFormatter {

    public static String maskNumber(float f){
        try{
            return String.format(Locale.getDefault(), "%.2f", f);
        }catch(Exception e){
            Log.e("Format error", f+" cannot be formatted to String format: #.##");
            return String.valueOf(f);
        }
    }

}
