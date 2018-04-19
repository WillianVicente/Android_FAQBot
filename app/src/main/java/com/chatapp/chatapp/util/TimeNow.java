package com.chatapp.chatapp.util;

import java.util.Calendar;

public class TimeNow {

    public static String TimeIsNow(){
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE);
    }
}
