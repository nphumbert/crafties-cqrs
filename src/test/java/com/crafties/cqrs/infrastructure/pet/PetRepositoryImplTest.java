package com.crafties.cqrs.infrastructure.pet;

import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetId;
import com.crafties.cqrs.model.pet.PetRepository;
import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PetRepositoryImplTest {

    private DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/create-db.sql")
                .addScript("pet/insert-test-data.sql")
                .build();
    }

    @Test
    public void should_find_pets() {
        // given
        PetRepository petRepository = new PetRepositoryImpl(dataSource());

        // when
        List<Pet> pets = petRepository.findPets();

        // then
        assertThat(pets).containsExactly(new Pet(new PetId(1L), "Norman", PetType.CAT), new Pet(new PetId(2L), "Sprit", PetType.DOG));
    }
}