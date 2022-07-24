package com.loyalty.jshan.global;

import com.loyalty.jshan.transaction.domain.enums.SourceType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

    public static String generateAuthNumber(SourceType authType) {

        LocalDateTime now = LocalDateTime.now();

        String authNumber = String.valueOf(now.getYear());

        switch (authType) {
            case FLIGHT:
                authNumber += "A";
                break;
            case CREDIT_CARD :
                authNumber += "C";
                break;
            case HOTEL:
                authNumber += "H";
                break;
            case OTHERS:
                authNumber += "O";
                break;
        }

        authNumber += now.format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));

        return authNumber;
    }
}
