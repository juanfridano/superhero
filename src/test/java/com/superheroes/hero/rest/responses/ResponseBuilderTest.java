package com.superheroes.hero.rest.responses;

import com.superheroes.hero.TestSupport;
import com.superheroes.hero.domain.Hero;
import com.superheroes.hero.rest.responses.entities.HeroEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ResponseBuilderTest extends TestSupport {

    @Autowired
    ResponseBuilder responseBuilder;

    @Test
    public void createHeroEntity(){
        Hero hero = random.nextObject(Hero.class);
        HeroEntity heroEntity = responseBuilder.createHeroEntity(hero);
        Assert.assertTrue(Objects.equals(hero.getId(), heroEntity.getId()));
        Assert.assertTrue(hero.getPseudonym().equals(heroEntity.getPseudonym()));
        Assert.assertTrue(hero.getName().equals(heroEntity.getName()));
        Assert.assertTrue(hero.getPublisher().equals(heroEntity.getPublisher()));
        Assert.assertTrue(hero.getSkills().equals(heroEntity.getSkills()));

    }

    @Test
    public void transformDate() throws Exception{
        String date = "2018-11-01";

        Hero hero = random.nextObject(Hero.class);
        hero.setFirstAppearence(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        HeroEntity heroEntity = responseBuilder.createHeroEntity(hero);
        heroEntity.getFirstAppearence();

        Assert.assertTrue(date.equals(heroEntity.getFirstAppearence()));
    }
}
