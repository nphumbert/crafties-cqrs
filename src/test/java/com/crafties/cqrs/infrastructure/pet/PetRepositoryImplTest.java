package com.crafties.cqrs.infrastructure.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetId;
import com.crafties.cqrs.model.pet.PetRepository;
import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PetRepositoryImplTest {

    private DataSource dataSource(String dataScript) {
        return new EmbeddedDatabaseBuilder()
                .addScript("db/create-db.sql")
                .addScript(dataScript)
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .build();
    }

    @Test
    public void should_retrieve_owner_of_pet() throws SQLException {
        // given
        PetRepository petRepository = new PetRepositoryImpl(dataSource("pet/insert-pet-with-owner.sql"));

        // when
        List<Pet> pets = petRepository.findPets();

        // then
        assertThat(pets).hasSize(1);
        assertThat(pets.get(0).owner()).isEqualTo(new Owner(new OwnerId(1L), "Lucie"));
    }

    @Test
    public void should_find_pets() throws SQLException {
        // given
        PetRepository petRepository = new PetRepositoryImpl(dataSource("pet/insert-pets.sql"));

        // when
        List<Pet> pets = petRepository.findPets();

        // then
        assertThat(pets).containsExactly(new Pet(new PetId(1L), "Norman", PetType.CAT, null), new Pet(new PetId(2L), "Sprit", PetType.DOG, null));
    }
}