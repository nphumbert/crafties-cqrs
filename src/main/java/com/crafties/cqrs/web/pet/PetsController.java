package com.crafties.cqrs.web.pet;

import com.crafties.cqrs.model.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PetsController {

    private final PetService petService;

    @Autowired
    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String getPets(final Model model) {
        model.addAttribute("pets", petService.findPets());
        model.addAttribute("navigationItem", "pets");
        return "pets";
    }
}
