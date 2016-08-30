package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerRepository;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PetServiceImplTest {

    @Test
    public void should_find_pets() {
        // given
        PetRepository petRepository = mock(PetRepository.class);
        when(petRepository.findPets()).thenReturn(asList(new Pet(new PetId(1L), "Norman", PetType.CAT, null), new Pet(new PetId(2L), "Sprit", PetType.DOG, null)));

        PetService petService = new PetServiceImpl(petRepository, null);

        // when
        List<Pet> pets = petService.findPets();

        // then
        assertThat(pets).containsExactly(new Pet(new PetId(1L), "Norman", PetType.CAT, null), new Pet(new PetId(2L), "Sprit", PetType.DOG, null));
    }

    @Test
    public void should_create_pet() {
        // given
        OwnerRepository ownerRepository = mock(OwnerRepository.class);
        Owner robert = new Owner(new OwnerId(1L), "Robert");
        when(ownerRepository.find(new OwnerId(1L))).thenReturn(robert);

        PetRepository petRepository = mock(PetRepository.class);
        PetService petService = new PetServiceImpl(petRepository, ownerRepository);

        // when
        Pet pet = petService.create("Norman", PetType.DOG, new OwnerId(1L));

        // then
        assertThat(pet.name()).isEqualTo("Norman");
        assertThat(pet.type()).isEqualTo(PetType.DOG);
        assertThat(pet.owner()).isEqualTo(robert);
        verify(petRepository).save(pet);
    }
}