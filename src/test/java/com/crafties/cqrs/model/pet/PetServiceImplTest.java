package com.crafties.cqrs.model.pet;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PetServiceImplTest {
    
    @Test
    public void should_find_pets() {
        // given
        PetRepository petRepository = mock(PetRepository.class);
        when(petRepository.findPets()).thenReturn(asList(new Pet(new PetId(1L), "Norman", PetType.CAT, null), new Pet(new PetId(2L), "Sprit", PetType.DOG, null)));

        PetService petService = new PetServiceImpl(petRepository);

        // when
        List<Pet> pets = petService.findPets();

        // then
        assertThat(pets).containsExactly(new Pet(new PetId(1L), "Norman", PetType.CAT, null), new Pet(new PetId(2L), "Sprit", PetType.DOG, null));
    }
}