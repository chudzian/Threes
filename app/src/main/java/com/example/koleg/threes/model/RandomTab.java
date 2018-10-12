package com.example.koleg.threes.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by patryk on 02.03.2018.
 */

public class RandomTab {

    private ArrayList<Integer> randomArrayList;
    private int from;
    private int to;
    private int[] numbertab;

    public int[] getNumbertab() {
        return numbertab;
    }
    public void setNumbertab(int[] numbertab) {
        this.numbertab = numbertab;
    }
    public ArrayList<Integer> getRandomArrayList() {
        return randomArrayList;
    }
    public void setRandomArrayList(ArrayList<Integer> randomArrayList) {
        this.randomArrayList = randomArrayList;
    }
    public int getArrayLength() {
        return randomArrayList.size();
    }
    public void setArrayLength(int arrayLength) {
        int arrayLength1 = arrayLength;
    }
    public int getFrom() {
        return from;
    }
    public void setFrom(int from) {
        this.from = from;
    }
    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }



    public RandomTab(int from, int to) {
        randomArrayList = new ArrayList<>();
        this.from = from;
        this.to = to;
        for (int i = 0 ; i <(to-this.from) ; i++) {
            randomArrayList.add(from);
            from++;
        }
        Collections.shuffle(randomArrayList);
        int[] ret = new int[randomArrayList.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = randomArrayList.get(i).intValue();
        }
        setNumbertab(ret);
    }
}