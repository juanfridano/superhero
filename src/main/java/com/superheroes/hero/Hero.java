package com.superheroes.hero;

import java.util.Date;
import java.util.List;

public class Hero {

    private Integer id;
    private String pseudonym;
    private Publisher publisher;
    private List<String> skills;
    private List<String> allies;
    private Date firstAppearence;

    public Hero(Integer id, String pseudonym, Publisher publisher, List<String> skills, List<String> allies, Date firstAppearence) {
        this.id = id;
        this.pseudonym = pseudonym;
        this.publisher = publisher;
        this.skills = skills;
        this.allies = allies;
        this.firstAppearence = firstAppearence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getAllies() {
        return allies;
    }

    public void setAllies(List<String> allies) {
        this.allies = allies;
    }

    public Date getFirstAppearence() {
        return firstAppearence;
    }

    public void setFirstAppearence(Date firstAppearence) {
        this.firstAppearence = firstAppearence;
    }
}
