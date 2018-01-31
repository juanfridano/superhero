package com.superheroes.hero.rest.controller;

import com.superheroes.hero.domain.Hero;
import com.superheroes.hero.domain.Publisher;
import com.superheroes.hero.exception.InvalidRequestArgumentException;
import com.superheroes.hero.repository.HeroRepositorySupport;
import com.superheroes.hero.rest.responses.ResponseBuilder;
import com.superheroes.hero.rest.responses.entities.HeroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class HeroController {


    @Autowired
    HeroRepositorySupport heroRepositorySupport;

    @Autowired
    ResponseBuilder responseBuilder;

    @RequestMapping("/hero/{id}")
    public ResponseEntity<HeroEntity> getOne(@PathVariable String id){

        checkExists(id);

        Hero hero = heroRepositorySupport.findOne(Integer.parseInt(id));

        return new ResponseEntity<>(responseBuilder.createHeroEntity(hero), HttpStatus.OK);
    }

    @RequestMapping("/create")
    public ResponseEntity<HeroEntity> createOne(@RequestParam String pseudonym, String name,
                                          String publisher, String skills, String alliesId, String firstAppearence){

        checkProperties(pseudonym, name, alliesId, firstAppearence, publisher);
        Hero hero = heroRepositorySupport.createEntityFromParameters(pseudonym, name,
                publisher, separateCSV(skills), separateCSV(alliesId), getDate(firstAppearence));

        return new ResponseEntity<>(responseBuilder.createHeroEntity(hero), HttpStatus.OK);
    }

    @RequestMapping("/delete")
    public ResponseEntity<Hero> deleteOne(@RequestParam String id){

        checkExists(id);
        heroRepositorySupport.deleteOne(Integer.parseInt(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/findall")
    public ResponseEntity<List<HeroEntity>> findAll(){

        List<Hero> heroes = heroRepositorySupport.findAll();

        return new ResponseEntity<>(responseBuilder.createHeroesEntities(heroes), HttpStatus.OK);
    }

    private Date getDate(String firstAppearence) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(firstAppearence);
        } catch (ParseException e) {
            throw new InvalidRequestArgumentException("Invalid date format YYYY-MM-DD");
        }

        return date;
    }

    private List<String> separateCSV(String csv) {
        return Arrays.asList(csv.split("\\s*,\\s*"));
    }

    private void checkExists(String id) {
        if(!heroRepositorySupport.existsById(Integer.parseInt(id))){
            throw new InvalidRequestArgumentException("Hero with te Id: " + id + " does not exist");
        }
    }
    private void checkProperties(String pseudonym, String name, String alliesId, String firstAppearence, String publisher) {
        if (pseudonym == null || pseudonym.isEmpty()){
            throw new InvalidRequestArgumentException("parameter pseudonym should not be null or empty");
        }

        if (name == null || name.isEmpty()){
            throw new InvalidRequestArgumentException("parameter name should not be null or empty");
        }

        if (firstAppearence == null || firstAppearence.isEmpty()){
            throw new InvalidRequestArgumentException("parameter firstAppearence should not be null or empty");
        }

        //Exception thrown from called method in case date is invalid
        getDate(firstAppearence);

        //Exception thrown from called method in case id is invalid
        for(String id :separateCSV(alliesId)){
            if (id != null && !id.isEmpty()){
                checkExists(id);
            }
        }

        if (publisher == null || publisher.isEmpty()){
            throw new InvalidRequestArgumentException("parameter publisher should not be null or empty");
        }

        try{
            Publisher.valueOf(publisher.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new InvalidRequestArgumentException("Publisher: " + publisher + " does not exist, only DC or Marvel are allowed");
        }
    }
}
