package com.appbusters.robinkamboj.practicingfirebase.model;

import java.util.HashMap;

public class User {

    private String name;
    private String email;
    private String bio;
    private String preferredLang;
    private String photoUrl;
    private String coverUrl;
    private HashMap<String, Boolean> motivation, religion, astrology, yoga, ayurveda, health, diet;
    private Boolean standardUser = Boolean.TRUE;
    private HashMap<String, HashMap<String, Boolean>> allInterests;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String bio, String preferredLang, String photoUrl, String coverUrl, HashMap<String, HashMap<String, Boolean>> allInterests, Boolean standardUser) {
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.preferredLang = preferredLang;
        this.photoUrl = photoUrl;
        this.coverUrl = coverUrl;
        this.allInterests = allInterests;
//        this.motivation = motivation;
//        this.religion = religion;
//        this.astrology = astrology;
//        this.yoga = yoga;
//        this.ayurveda = ayurveda;
//        this.health = health;
//        this.diet = diet;
        this.standardUser = standardUser;
    }

    public HashMap<String, HashMap<String, Boolean>> getAllInterests() {
        return allInterests;
    }

    public void setAllInterests(HashMap<String, HashMap<String, Boolean>> allInterests) {
        this.allInterests = allInterests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPreferredLang() {
        return preferredLang;
    }

    public void setPreferredLang(String preferredLang) {
        this.preferredLang = preferredLang;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public HashMap<String, Boolean> getMotivation() {
        return motivation;
    }

    public void setMotivation(HashMap<String, Boolean> motivation) {
        this.motivation = motivation;
    }

    public HashMap<String, Boolean> getReligion() {
        return religion;
    }

    public void setReligion(HashMap<String, Boolean> religion) {
        this.religion = religion;
    }

    public HashMap<String, Boolean> getAstrology() {
        return astrology;
    }

    public void setAstrology(HashMap<String, Boolean> astrology) {
        this.astrology = astrology;
    }

    public HashMap<String, Boolean> getYoga() {
        return yoga;
    }

    public void setYoga(HashMap<String, Boolean> yoga) {
        this.yoga = yoga;
    }

    public HashMap<String, Boolean> getAyurveda() {
        return ayurveda;
    }

    public void setAyurveda(HashMap<String, Boolean> ayurveda) {
        this.ayurveda = ayurveda;
    }

    public HashMap<String, Boolean> getHealth() {
        return health;
    }

    public void setHealth(HashMap<String, Boolean> health) {
        this.health = health;
    }

    public HashMap<String, Boolean> getDiet() {
        return diet;
    }

    public void setDiet(HashMap<String, Boolean> diet) {
        this.diet = diet;
    }

    public Boolean getStandardUser() {
        return standardUser;
    }

    public void setStandardUser(Boolean standardUser) {
        this.standardUser = standardUser;
    }
}
