package com.crafties.cqrs.model.owner;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OwnerServiceImplTest {

    @Test
    public void should_find_owners() {
        // given
        OwnerRepository ownerRepository = mock(OwnerRepository.class);
        OwnerService ownerService = new OwnerServiceImpl(ownerRepository);
        when(ownerRepository.findOwners()).thenReturn(Arrays.asList(new Owner(new OwnerId(1L), "Robert"), new Owner(new OwnerId(2L), "Laure")));

        // when
        List<Owner> owners = ownerService.findOwners();

        // then
        assertThat(owners).containsExactly(new Owner(new OwnerId(1L), "Robert"), new Owner(new OwnerId(2L), "Laure"));
    }
}