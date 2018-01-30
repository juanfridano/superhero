package com.superheroes.hero;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSupport {

    protected EnhancedRandom random;

    @Before
    public void setup() {
        random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(new Date().getTime())
                .stringLengthRange(5, 5)
                .collectionSizeRange(1, 1)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .build();


    }
}
