package com.superheroes.hero.rest.responses;

import com.superheroes.hero.domain.Hero;
import com.superheroes.hero.repository.HeroRepositorySupport;
import com.superheroes.hero.rest.responses.entities.HeroEntity;
import com.superheroes.hero.rest.responses.entities.HeroEntityPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ResponseBuilder {

    @Autowired
    HeroRepositorySupport heroRepositorySupport;

    public HeroEntity createHeroEntity(Hero hero){

        return new HeroEntity()
                .withId(hero.getId())
                .withPseudonym(hero.getPseudonym())
                .withName(hero.getName())
                .withPublisher(hero.getPublisher())
                .withSkills(hero.getSkills())
                .withAllies(createListOfPreviews(hero.getAlliesId()))
                .withFirstAppearence(getDate(hero.getFirstAppearence()));
    }

    public List<HeroEntity> createHeroesEntities(List<Hero> heroes){
        List<HeroEntity> result = new ArrayList<>();
        for (Hero hero : heroes){
            result.add(createHeroEntity(hero));
        }
        return result;
    }

    private List<HeroEntityPreview> createListOfPreviews(List<Integer> alliesId) {
        List<HeroEntityPreview> result = new ArrayList<>();
        for(Integer id : alliesId){
            if(heroRepositorySupport.existsById(id)){
                HeroEntityPreview preview = new HeroEntityPreview();
                preview.setPseudonym(heroRepositorySupport.findOne(id).getPseudonym());
                preview.setLink(String.valueOf(id));
                result.add(preview);
            }
        }

        return result;
    }

    private String getDate(Date firstAppearence) {
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        return df.format(firstAppearence);
    }
}