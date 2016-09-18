package com.crafties.cqrs.web.pet;

import com.crafties.cqrs.facade.pets.PetsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PetsController {

    private final PetsFacade petsFacade;

    @Autowired
    public PetsController(PetsFacade petsFacade) {
        this.petsFacade = petsFacade;
    }

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String getPets(final Model model) {
        model.addAttribute("pets", petsFacade.findPets());
        model.addAttribute("navigationItem", "pets");
        return "pets";
    }
}
