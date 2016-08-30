package com.crafties.cqrs.web.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetId;
import com.crafties.cqrs.model.pet.PetService;
import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PetsController.class)
public class PetsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    public void should_display_pets() throws Exception {
        // given
        Pet lafayette = new Pet(new PetId(1L), "lafayette", PetType.DOG, new Owner(new OwnerId(1L), "Robert"));
        Pet berlioz = new Pet(new PetId(2L), "berlioz", PetType.CAT, new Owner(new OwnerId(2L), "Laure"));
        when(petService.findPets()).thenReturn(Arrays.asList(lafayette, berlioz));

        // when and then
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets"))
                .andExpect(model().attribute("navigationItem", is("pets")))
                .andExpect(model().attribute("pets", contains(lafayette, berlioz)));
    }
}
