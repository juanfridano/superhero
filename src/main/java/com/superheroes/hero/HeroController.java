package com.superheroes.hero;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class HeroController {

    @RequestMapping("/")
    public ResponseEntity<String> getOne(){

        ObjectMapper mapperBuilder = new ObjectMapper();

        Hero hero = new Hero(1, "ds", Publisher.MARVEL, Arrays.asList("asdasd"),
                Arrays.asList("asdasd"), new Date() );
        String heroString ="";

        try {
            heroString = mapperBuilder.writeValueAsString(hero);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(heroString, HttpStatus.OK);
    }
}
