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

    public static String maskMoney(float f){
        try{
            return String.format(Locale.getDefault(), "$ %.2f", f);
        }catch(Exception e){
            Log.e("Format error", f+" cannot be formatted to String format: #.##");
            return String.valueOf(f);
        }
    }

    public static float toFloat(String s) {
        try{
            return Float.valueOf(s);
        }catch (Exception e){
            Log.e("Format error", s+" cannot be converted to float");
            return 0.0F;
        }
    }

    public static int toInt(String s) {
        try{
            return Integer.valueOf(s);
        }catch (Exception e){
            Log.e("Format error", s+" cannot be converted to integer");
            return 1;
        }
    }
}
