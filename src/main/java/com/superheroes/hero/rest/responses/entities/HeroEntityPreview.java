package com.superheroes.hero.rest.responses.entities;

import com.superheroes.hero.rest.controller.HeroController;
import org.springframework.hateoas.Link;

import java.lang.reflect.Method;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class HeroEntityPreview {

    private String pseudonym;
    private Link link;

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(String id) {
        Method method = null;
        try {
            method = HeroController.class.getMethod("getOne", String.class);
        } catch (NoSuchMethodException e) {
            this.link = null;
        }
        this.link = linkTo(method, id).withSelfRel();
    }
}
