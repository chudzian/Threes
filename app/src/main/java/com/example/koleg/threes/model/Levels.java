package com.example.koleg.threes.model;

import java.util.ArrayList;

/**
 * Created by patryk on 09.03.2018.
 */

public class Levels {

    private ArrayList<Level> levels;

    public Levels() {
        ArrayList<Level>  levels = new ArrayList<>();

        levels.add(new Level(3,100, 0, 13));
        levels.add(new Level(101,200, 14, 43));
        levels.add(new Level(201,310, 44, 100));
        levels.add(new Level(396,720, 101, 206));
        levels.add(new Level(721,999, 206, 419));
        levels.add(new Level(1000,1310, 420, 2000));

//        levels.add(new Level(3,100,  13));
//        levels.add(new Level(101,200, 31));
//        levels.add(new Level(201,310, 56));
//        levels.add(new Level(396,720, 100));
//        levels.add(new Level(721,999, 220));
//        levels.add(new Level(1000,1310, 300));

        setLevels(levels);
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
}

