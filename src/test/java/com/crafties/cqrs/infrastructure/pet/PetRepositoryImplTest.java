package com.crafties.cqrs.infrastructure.pet;

import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetId;
import com.crafties.cqrs.model.pet.PetRepository;
import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.crafties.cqrs.fixture.DataSourceBuilder.aDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PetRepositoryImplTest {

    @Test
    public void should_save_pet() {
        // given
        DataSource dataSource = aDataSource().build();
        PetRepository petRepository = new PetRepositoryImpl(dataSource);
        Pet pet = new Pet(new PetId(3L), "Norbert", PetType.DOG, null);

        // when
        petRepository.save(pet);

        // then
        Long petNumber = new JdbcTemplate(dataSource).queryForObject("SELECT COUNT(*) FROM pet WHERE id = ? AND name = ? AND type = ?", Long.class, 3, "Norbert", PetType.DOG.name());
        assertThat(petNumber).isEqualTo(1L);
    }
}