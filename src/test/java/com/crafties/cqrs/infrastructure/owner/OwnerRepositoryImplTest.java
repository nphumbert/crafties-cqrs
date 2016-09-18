package com.crafties.cqrs.infrastructure.owner;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerRepository;
import org.junit.Test;

import static com.crafties.cqrs.fixture.DataSourceBuilder.aDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class OwnerRepositoryImplTest {

    @Test
    public void should_find_owner() {
        // given
        OwnerRepository ownerRepository = new OwnerRepositoryImpl(aDataSource().withDataScript("owner/insert-owners.sql").build());

        // when
        Owner owner = ownerRepository.find(new OwnerId(1L));

        // then
        assertThat(owner.getId()).isEqualTo(new OwnerId(1L));
        assertThat(owner.getName()).isEqualTo("Robert");
    }

}