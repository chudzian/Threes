package com.example.koleg.threes.util;


public class Conditions {

    public static boolean isDivisibleByThree(int number){
        return number%3 == 0 ? true : false;
    }

    public static boolean succesCondition(int number){
        boolean containsThree = String.valueOf(number).contains("3");
        boolean isDivisible =  number%3==0;
        return containsThree || isDivisible;
    }


}
