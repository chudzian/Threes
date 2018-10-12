package com.example.koleg.threes.model;



public class Level {



    private RandomTab randomTab;
    private int scopeFrom;
    private int scopeTo;
    private int progressScope;
    private int levelScope;



    public Level(int numbersFrom, int NumbersTo , int scopeFrom, int scopeTo) {
        this.randomTab = new RandomTab(numbersFrom,NumbersTo);
        this.scopeFrom = scopeFrom;
        this.scopeTo = scopeTo;
        this.progressScope = scopeTo - scopeFrom;
    }

    public Level(int numbersFrom, int NumbersTo , int progressScope) {
        this.randomTab =  new RandomTab(numbersFrom,NumbersTo);
        this.progressScope = progressScope;
    }

    public int getLevelScope() {
        return levelScope;
    }

    public void setLevelScope(int levelScope) {
        this.levelScope = levelScope;
    }
    public int getProgressScope() {
        return progressScope;
    }

    public void setProgressScope(int progressScope) {
        this.progressScope = progressScope;
    }

    public RandomTab getRandomTab() {
        return randomTab;
    }

    public void setRandomTab(RandomTab randomTab) {
        this.randomTab = randomTab;
    }

    public int getScopeFrom() {
        return scopeFrom;
    }

    public void setScopeFrom(int scopeFrom) {
        this.scopeFrom = scopeFrom;
    }

    public int getScopeTo() {
        return scopeTo;
    }

    public void setScopeTo(int scopeTo) {
        this.scopeTo = scopeTo;
    }
}
