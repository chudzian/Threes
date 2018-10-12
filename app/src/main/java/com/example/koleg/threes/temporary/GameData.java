package com.example.koleg.threes.temporary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameData {
    int[] levelLenghtsArray;
    int[] gameArray;
    List<tLevel> levels;
    List<List<Integer>> levelsNumbers;

    public GameData() {
        this.levels = initializeLevels();
        this.levelLenghtsArray = initializeLevelLengthsArray();
        this.levelsNumbers = createLevelsNumbers(levels);
        this.gameArray = generateGameArray(levelsNumbers,levelLenghtsArray);
    }

    private List<tLevel> initializeLevels(){   // levelLength must be less than numbersTo - numbersFrom
        levels = new ArrayList<>();
        levels.add(new tLevel(3,100,13));
        levels.add(new tLevel(101,200,29));
        levels.add(new tLevel(201,310,56));
        levels.add(new tLevel(396,720,105));
        levels.add(new tLevel(721,999,213));
        levels.add(new tLevel(1000,1310,309));
        levels.add(new tLevel(1396,1900,500));
        return levels;
    }

    private int[] initializeLevelLengthsArray(){
        int[] levelLenghtsArray = new int[levels.size()];
        for(int i = 0; i < levels.size(); i++){
            levelLenghtsArray[i] = levels.get(i).getLevelLenght();
        }
        return levelLenghtsArray;
    }

    private List<List<Integer>> createLevelsNumbers(List<tLevel> levels){
        List<List<Integer>> levelsNumbers = new ArrayList<>();
        for(int i = 0; i < levels.size() ; i++) {
            levelsNumbers.add(createShuffledArrayList(levels.get(i)));
        }
        return levelsNumbers;
    }

    private List<Integer> createShuffledArrayList(tLevel level){
        List<Integer> shuffledArrayList = new ArrayList<>();
        int forLoopEnd = level.getNumbersTo()-level.getNumbersFrom();
        int numberToAdd = level.getNumbersFrom();
        for(int i = 0; i < forLoopEnd ; i++){
            shuffledArrayList.add(numberToAdd);
            numberToAdd++;
        }
        Collections.shuffle(shuffledArrayList);
        return shuffledArrayList;
    }

    private int[] generateGameArray(List<List<Integer>> levelsNumbers, int[] levelLenghtsArray){
        int gameLength = sumIntInArray(levelLenghtsArray);
        int[] gameArray = new int[gameLength];
        int level = 0;
        int iterator = 0;
        for(int i = 0; i < gameLength; i++){
            gameArray[i] = levelsNumbers.get(level).get(iterator);
            if(levelLenghtsArray[level] == iterator && level<levelsNumbers.size()-1){
                level++;
                iterator=0;
            }
            iterator++;
        }
        return gameArray;
    }


    private int sumIntInArray(int[] arrayToSum){
        int sum = 0;
        for(int i: arrayToSum){
            sum+=i;
        }
        return sum;
    }

    public int[] getLevelLenghtsArray() {
        return levelLenghtsArray;
    }

    public int[] getGameArray() {
        return gameArray;
    }
}
