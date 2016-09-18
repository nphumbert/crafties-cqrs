package com.crafties.cqrs.web.owner;

import com.crafties.cqrs.facade.OwnerDto;
import com.crafties.cqrs.facade.OwnersFacade;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnersController.class)
public class OwnersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnersFacade ownersFacade;

    @Test
    public void should_find_owners() throws Exception {
        // given
        OwnerDto robert = new OwnerDto("Robert");
        OwnerDto laure = new OwnerDto("Laure");
        when(ownersFacade.findOwners()).thenReturn(asList(robert, laure));

        // when and then
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners"))
                .andExpect(model().attribute("navigationItem", is("owners")))
                .andExpect(model().attribute("owners", contains(robert, laure)));
    }
}