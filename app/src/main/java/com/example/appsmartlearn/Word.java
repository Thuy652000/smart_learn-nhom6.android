package com.example.appsmartlearn;

public class Word {
    public String engWord;
    public String meaning;
    public String type;
    public String read;
    public String ex;
    public String meaning2;
    public String ex2;
    public Word() {
    }

    public Word(String engWord) {
        this.engWord = engWord;
        this.meaning = meaning;
        this.meaning2 = meaning2;
        this.type = type;
        this.ex = ex;
        this.ex2 = ex2;
        this.read = read;
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

    public String getRead() { return read;}
    public void setRead(String read) {this.read = read;}

    public String getEx() { return ex;}
    public void setEx(String ex) {this.ex = ex;}
    public String getMeaning2() { return meaning2;}
    public void setMeaning2(String meaning2) {this.meaning2 = meaning2;}
    public String getEx2() { return ex2;}
    public void setEx2(String ex2) {this.ex2 = ex2;}

}
