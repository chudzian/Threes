package com.example.koleg.threes.temporary;



public class tLevel {
    int numbersFrom;
    int numbersTo;
    int levelLenght;

    public tLevel(int numbersFrom, int numbersTo, int levelLenght) {
        this.numbersFrom = numbersFrom;
        this.numbersTo = numbersTo;
        this.levelLenght = levelLenght;
    }

    public int getNumbersFrom() {
        return numbersFrom;
    }

    public int getNumbersTo() {
        return numbersTo;
    }

    public int getLevelLenght() {
        return levelLenght;
    }
}
