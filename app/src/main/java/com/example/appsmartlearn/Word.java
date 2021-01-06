package com.example.appsmartlearn;

public class Word {
    public String engWord;
    public String meaning;
    public String type;
    public Word() {
    }

    public Word(String engWord) {
        this.engWord = engWord;
        this.meaning = meaning;
        this.type = type;
    }

    public String getEngWord() {
        return engWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
