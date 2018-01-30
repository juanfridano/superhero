package com.superheroes.hero.repository.jpa;

import com.superheroes.hero.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Serializable>{
    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Hero h WHERE h.id = :id")
    boolean existsById(@Param("id") Integer id);

}
