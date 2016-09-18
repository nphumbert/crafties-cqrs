package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.OwnerId;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PetServiceImplTest {

    @Test
    public void should_create_pet() {
        // given
        PetRepository petRepository = mock(PetRepository.class);
        PetService petService = new PetServiceImpl(petRepository);

        // when
        Pet pet = petService.create("Norman", PetType.DOG, new OwnerId(1L));

        // then
        assertThat(pet.getName()).isEqualTo("Norman");
        assertThat(pet.getType()).isEqualTo(PetType.DOG);
        assertThat(pet.getOwnerId()).isEqualTo(new OwnerId(1L));
        verify(petRepository).save(pet);
    }
}