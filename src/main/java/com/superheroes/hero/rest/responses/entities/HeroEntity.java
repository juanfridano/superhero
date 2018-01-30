package com.superheroes.hero.rest.responses.entities;

import com.superheroes.hero.domain.Publisher;

import java.util.List;

public class HeroEntity {

    private Integer id;
    private String pseudonym;
    private String name;
    private Publisher publisher;
    private List<String> skills;
    private List<HeroEntityPreview> allies;
    private String firstAppearence;

    public HeroEntity withId(Integer id) {
        this.id = id;
        return this;
    }
    
    public HeroEntity withPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
        return this;
    }

    public HeroEntity withName(String name) {
        this.name = name;
        return this;
    }

    
    public HeroEntity withPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public HeroEntity withSkills(List<String> skills) {
        this.skills = skills;
        return this;
    }
    
    public HeroEntity withAllies(List<HeroEntityPreview> allies) {
        this.allies = allies;
        return this;
    }

    public HeroEntity withFirstAppearence(String firstAppearence) {
        this.firstAppearence = firstAppearence;
        return this;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<HeroEntityPreview> getAllies() {
        return allies;
    }

    public void setAllies(List<HeroEntityPreview> allies) {
        this.allies = allies;
    }

    public String getFirstAppearence() {
        return firstAppearence;
    }

    public void setFirstAppearence(String firstAppearence) {
        this.firstAppearence = firstAppearence;
    }
}
