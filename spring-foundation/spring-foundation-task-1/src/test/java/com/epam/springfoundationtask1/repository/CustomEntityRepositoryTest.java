package com.epam.springfoundationtask1.repository;

import com.epam.springfoundationtask1.entity.CustomEntity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CustomEntityRepositoryTest {

    @Autowired
    private CustomEntityRepository entityRepository;

    @Test
    public void saveEntityTest() {
        //given
        CustomEntity customEntity = CustomEntity.builder()
            .name("customEntityTestName")
            .build();

        //when
        entityRepository.save(customEntity);
        List<CustomEntity> actual = entityRepository.findAll();

        //then
        Assertions.assertThat(actual).isNotEmpty();
    }
}
