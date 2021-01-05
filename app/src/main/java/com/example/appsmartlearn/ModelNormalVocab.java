package com.example.appsmartlearn;

public class ModelNormalVocab {
    private String Eng;
    private String Vie;

    public ModelNormalVocab() {
    }

    public ModelNormalVocab(String vie, String eng) {
        this.Vie = vie;
        this.Eng= eng;
    }

    public String getVie() {
        return Vie;
    }

    public void setVie(String vie) {
        Vie = vie;
    }

    public String getEng() {
        return Eng;
    }

    public void setEng(String eng) {
        Eng = eng;
    }


}
