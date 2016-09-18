package com.crafties.cqrs.facade;

import org.junit.Test;

import java.util.List;

import static com.crafties.cqrs.fixture.DataSourceBuilder.aDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class OwnersFacadeImplTest {

    @Test
    public void should_find_owners() {
        // given
        OwnersFacade ownersFacade = new OwnersFacadeImpl(aDataSource().withDataScript("owner/insert-owners-with-pets.sql").build());

        // when
        List<OwnerDto> owners = ownersFacade.findOwners();

        // then
        assertThat(owners).containsExactly(new OwnerDto("Robert", 1L), new OwnerDto("Laure", 0L));
    }

}