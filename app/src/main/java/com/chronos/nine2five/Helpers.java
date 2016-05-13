package com.chronos.nine2five;

/**
 * Created by user on 16/04/2016.
 */
public class Helpers {
    public static String FormatTimeString(long elapsed){
        return  String.format("%02d",(elapsed / (60 * 60 * 1000) % 24)) + ":" + // Hours
                String.format("%02d",(elapsed / (60 * 1000) % 60)) + ":" + // Minutes
                String.format("%02d",(elapsed / 1000 % 60)); // Seconds
    }
}
