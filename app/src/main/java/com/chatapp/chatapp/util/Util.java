package com.chatapp.chatapp.util;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class Util {

    //Recupera o horario atual
    public static String TimeIsNow(){
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE);
    }

    //Remove os acentos das mensagens enviadas
    public static String RemoverAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    //Gera o ID do usuario
    public static String RandomGuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
