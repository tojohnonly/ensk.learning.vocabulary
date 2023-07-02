package com.ensk.study.vocabulary;

public class WordEntity {

    private Integer id;
    private String word;
    private String pronounce;
    private String translation;
    private String example;
    private Integer learnTimes;
    private Integer dnkTimes;
    private Integer hmTimes;
    private Integer kimTimes;
    private Float learnScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getLearnTimes() {
        return learnTimes;
    }

    public void setLearnTimes(Integer learnTimes) {
        this.learnTimes = learnTimes;
    }

    public Integer getDnkTimes() {
        return dnkTimes;
    }

    public void setDnkTimes(Integer dnkTimes) {
        this.dnkTimes = dnkTimes;
    }

    public Integer getHmTimes() {
        return hmTimes;
    }

    public void setHmTimes(Integer hmTimes) {
        this.hmTimes = hmTimes;
    }

    public Integer getKimTimes() {
        return kimTimes;
    }

    public void setKimTimes(Integer kimTimes) {
        this.kimTimes = kimTimes;
    }

    public Float getLearnScore() {
        return learnScore;
    }

    public void setLearnScore(Float learnScore) {
        this.learnScore = learnScore;
    }
}
