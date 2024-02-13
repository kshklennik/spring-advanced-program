package com.epam.springfoundationtask2.repository;

import com.epam.springfoundationtask2.entity.CustomEntitySecond;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CustomEntitySecondRepositoryTest {

    @Autowired
    private CustomEntitySecondRepository entityRepository;

    @Test
    public void saveEntityTest() {
        //given
        CustomEntitySecond customEntity = CustomEntitySecond.builder()
            .name("customEntityTestName")
            .build();

        //when
        entityRepository.save(customEntity);
        List<CustomEntitySecond> actual = entityRepository.findAll();

        //then
        Assertions.assertThat(actual).isNotEmpty();
    }
}
