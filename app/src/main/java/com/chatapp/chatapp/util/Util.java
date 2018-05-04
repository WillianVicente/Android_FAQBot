package com.chatapp.chatapp.util;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class Util {

    public static String TimeIsNow(){
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE);
    }

    public static String RemoverAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String RandomGuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
