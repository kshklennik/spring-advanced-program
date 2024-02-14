package com.epam.springprofilestask.repository;

import com.epam.springprofilestask.entity.CustomEntityThird;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomEntityThirdRepositoryTest {

    @Autowired
    private CustomEntityThirdRepository entityRepository;

    @Test
    public void saveEntityTest() {
        //given
        CustomEntityThird customEntity = CustomEntityThird.builder()
            .name("customEntityTestName")
            .build();

        //when
        entityRepository.save(customEntity);
        List<CustomEntityThird> actual = entityRepository.findAll();

        //then
        Assertions.assertThat(actual).isNotEmpty();
    }
}
