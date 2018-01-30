package com.superheroes.hero.repository;

import com.superheroes.hero.domain.Hero;
import com.superheroes.hero.domain.Publisher;
import com.superheroes.hero.repository.jpa.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class HeroRepositorySupport {

    @Autowired
    HeroRepository heroRepository;

    public Hero createEntityFromParameters(String pseudonym, String name, String publisher, List<String> skills, List<String> alliesId, Date firstAppearence) {
        List<Integer> allies = new ArrayList<>();
        for (String s : alliesId){
            if (!s.isEmpty()){
                allies.add(Integer.parseInt(s));
            }
        }

        Hero result = new Hero();
        result.setName(name);
        result.setPseudonym(pseudonym);
        result.setSkills(skills);
        result.setAlliesId(allies);
        result.setPublisher(Publisher.valueOf(publisher.toUpperCase()));
        result.setFirstAppearence(firstAppearence);
        heroRepository.save(result);
        return result;
    }


    public boolean existsById(int id) {
        return heroRepository.existsById(id);
    }

    public Hero findOne(int id) {
        return heroRepository.findOne(id);
    }

    public void deleteOne(int id) {
        heroRepository.delete(findOne(id));
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
}
