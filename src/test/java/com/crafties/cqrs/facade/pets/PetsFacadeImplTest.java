package com.crafties.cqrs.facade.pets;

import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static com.crafties.cqrs.fixture.DataSourceBuilder.aDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PetsFacadeImplTest {

    @Test
    public void should_find_pets_with_owner() throws SQLException {
        // given
        PetsFacade petsFacade = new PetsFacadeImpl(aDataSource().withDataScript("pet/insert-pets-with-owner.sql").build());

        // when
        List<PetDto> pets = petsFacade.findPets();

        // then
        assertThat(pets).containsExactly(new PetDto("Norman", PetType.CAT, "Lucie"), new PetDto("Sprit", PetType.DOG, "Robert"));
    }
}