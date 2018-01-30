package com.superheroes.hero.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String pseudonym;
    @Column
    private String name;
    @Column
    private Publisher publisher;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<Integer> alliesId;
    @Column
    private Date firstAppearence;

    public Hero() {
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

    public List<Integer> getAlliesId() {
        return alliesId;
    }

    public void setAlliesId(List<Integer> alliesId) {
        this.alliesId = alliesId;
    }

    public Date getFirstAppearence() {
        return firstAppearence;
    }

    public void setFirstAppearence(Date firstAppearence) {
        this.firstAppearence = firstAppearence;
    }
}
