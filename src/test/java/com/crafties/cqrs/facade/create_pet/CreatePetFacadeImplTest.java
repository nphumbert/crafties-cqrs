package com.crafties.cqrs.facade.create_pet;

import com.crafties.cqrs.model.owner.OwnerId;
import org.junit.Test;

import java.util.List;

import static com.crafties.cqrs.fixture.DataSourceBuilder.aDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CreatePetFacadeImplTest {

    @Test
    public void should_find_owners() {
        // given
        CreatePetFacade createPetFacade = new CreatePetFacadeImpl(aDataSource().withDataScript("owner/insert-owners.sql").build());

        // when
        List<OwnerDto> owners = createPetFacade.findOwners();

        // then
        assertThat(owners).containsExactly(new OwnerDto(new OwnerId(1L), "Robert"), new OwnerDto(new OwnerId(2L), "Laure"));
    }

}