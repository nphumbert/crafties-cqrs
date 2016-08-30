package com.crafties.cqrs.web.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerService;
import com.crafties.cqrs.model.pet.PetService;
import com.crafties.cqrs.model.pet.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @MockBean
    private OwnerService ownerService;

    @Test
    public void should_display_pet_creation_form() throws Exception {
        // given
        Owner robert = new Owner(new OwnerId(1L), "Robert");
        Owner laure = new Owner(new OwnerId(2L), "Laure");
        when(ownerService.findOwners()).thenReturn(asList(robert, laure));

        // when and then
        mockMvc.perform(get("/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pet"))
                .andExpect(model().attribute("navigationItem", is("pets")))
                .andExpect(model().attribute("owners", contains(robert, laure)));
    }

    @Test
    public void should_create_new_pet() throws Exception {
        // when and then
        mockMvc.perform(post("/pets/new")
                .param("name", "Sprit")
                .param("type", "DOG")
                .param("ownerId", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/pets"));

        verify(petService).create("Sprit", PetType.DOG, new OwnerId(1L));
    }

    @Test
    public void should_add_error_to_model_when_field_not_valid() throws Exception {
        // when and then
        mockMvc.perform(post("/pets/new")
                .param("name", "")
                .param("type", "DOG")
                .param("ownerId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("createPetCommand", "name"))
                .andExpect(view().name("pet"));
    }
}