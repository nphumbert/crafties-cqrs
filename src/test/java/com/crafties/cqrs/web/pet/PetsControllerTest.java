package com.crafties.cqrs.web.pet;

import com.crafties.cqrs.facade.pets.PetDto;
import com.crafties.cqrs.facade.pets.PetsFacade;
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
    private PetsFacade petsFacade;

    @Test
    public void should_display_pets() throws Exception {
        // given
        PetDto lafayette = new PetDto("lafayette", PetType.DOG, "Robert");
        PetDto berlioz = new PetDto("berlioz", PetType.CAT, "Laure");
        when(petsFacade.findPets()).thenReturn(Arrays.asList(lafayette, berlioz));

        // when and then
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets"))
                .andExpect(model().attribute("navigationItem", is("pets")))
                .andExpect(model().attribute("pets", contains(lafayette, berlioz)));
    }
}
