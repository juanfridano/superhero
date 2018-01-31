package com.superheroes.hero.rest.controller;

import com.superheroes.hero.domain.Hero;
import com.superheroes.hero.domain.Publisher;
import com.superheroes.hero.exception.InvalidRequestArgumentException;
import com.superheroes.hero.repository.HeroRepositorySupport;
import com.superheroes.hero.rest.responses.ResponseBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


@RunWith(SpringRunner.class)
@WebMvcTest(value = HeroController.class, secure = false)
public class HeroControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HeroRepositorySupport heroRepositorySupport;

    @MockBean
    ResponseBuilder responseBuilder;

    @Test
    public void getHero() throws Exception {

        ResponseBuilder realResponseBuilder = new ResponseBuilder();

        Hero mockHero = initializeHero();
        Mockito.when(heroRepositorySupport.findOne(Mockito.anyInt())).thenReturn(mockHero);
        Mockito.when(heroRepositorySupport.existsById(Mockito.anyInt())).thenReturn(true);

        Mockito.when(responseBuilder.createHeroEntity(Mockito.anyObject())).thenReturn(realResponseBuilder.createHeroEntity(mockHero));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/hero/11").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":" + mockHero.getId()+ ", \"pseudonym\":\""+ mockHero.getPseudonym()+"\"," +
                "\"name\":\""+ mockHero.getName()+"\",\"publisher\":\""+ mockHero.getPublisher()+"\",\"skills\":" +
                "[\"run fast\",\"run slower than everybody else\"],\"allies\":[]," +
                "\"firstAppearence\":\"2018-01-30\"}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void unexistingHeroThrowsExeption() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/hero/95648").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Hero with ID 95648 does not exist
        Assert.assertEquals(HttpStatus.valueOf(result.getResponse().getStatus()), HttpStatus.BAD_REQUEST);
    }


    private Hero initializeHero() {
        Hero hero = new Hero();
        hero.setId(3);
        hero.setFirstAppearence(new Date());
        hero.setPublisher(Publisher.MARVEL);
        hero.setAlliesId(new ArrayList<>());
        hero.setSkills(Arrays.asList("run fast", "run slower than everybody else"));
        hero.setName("Barry Allen");
        hero.setPseudonym("The Flash");
        return hero;
    }
}
