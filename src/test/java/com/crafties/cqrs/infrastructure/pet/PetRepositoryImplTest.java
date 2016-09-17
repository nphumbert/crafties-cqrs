package com.crafties.cqrs.infrastructure.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetId;
import com.crafties.cqrs.model.pet.PetRepository;
import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static com.crafties.cqrs.fixture.DataSourceBuilder.aDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PetRepositoryImplTest {

    @Test
    public void should_retrieve_owner_of_pet() throws SQLException {
        // given
        PetRepository petRepository = new PetRepositoryImpl(aDataSource().withDataScript("pet/insert-pet-with-owner.sql").build());

        // when
        List<Pet> pets = petRepository.findPets();

        // then
        assertThat(pets).hasSize(1);
        assertThat(pets.get(0).owner()).isEqualTo(new Owner(new OwnerId(1L), "Lucie"));
    }

    @Test
    public void should_find_pets() throws SQLException {
        // given
        PetRepository petRepository = new PetRepositoryImpl(aDataSource().withDataScript("pet/insert-pets.sql").build());

        // when
        List<Pet> pets = petRepository.findPets();

        // then
        assertThat(pets).containsExactly(new Pet(new PetId(1L), "Norman", PetType.CAT, null), new Pet(new PetId(2L), "Sprit", PetType.DOG, null));
    }

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