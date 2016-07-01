package org.song.java8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Store whehter a number is used from 800-000-0000 to 800-888-8888
 */
public class PhoneNumberRepository {
    private final BitMap map = new BitMap(8888888l);

    private final static Pattern PHONE_NUMBER_PATTERN=Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
    /**
     * Accepting xxx-xxx-xxxx
     * @param phoneNumber
     * @return
     */
    public boolean isPhoneNumberUsed(String phoneNumber){

        return !map.isSet(getLongFromString(phoneNumber));
    }

    public void setPhoneNumber(String phoneNumber){

        map.set(getLongFromString(phoneNumber));
    }

    public void clearPhoneNumber(String phoneNumber){
        map.clear(getLongFromString(phoneNumber));
    }

    private long getLongFromString(String phoneNumber){
        Matcher macther = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        if (!macther.find()){
            throw new IllegalArgumentException("Illegal phoneNumber and it must be xxx-xxx-xxxx while yours is: "+phoneNumber);
        }

        // remove the first 3 digits and all hyphens
        return Long.parseLong(phoneNumber.replaceAll("-", "").substring(3));

    }

    private static void test(String phoneNumber, PhoneNumberRepository pr){
        System.out.println("Checking the availability of "+phoneNumber + ". The number is" + (pr.isPhoneNumberUsed(phoneNumber)?"":" not") + " available.");
        pr.setPhoneNumber(phoneNumber);

        System.out.println("After setting "+phoneNumber + ". The number is" + (pr.isPhoneNumberUsed(phoneNumber)?"":" not") + " available.");
        pr.clearPhoneNumber(phoneNumber);

        System.out.println("After clearing "+phoneNumber + ". The number is" + (pr.isPhoneNumberUsed(phoneNumber)?"":" not") + " available.");

    }

    public static void main (String[] args){
        PhoneNumberRepository pr = new PhoneNumberRepository();
        test("800-522-7382", pr);
    }

}
