package com.crafties.cqrs.web.pet;

import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerService;
import com.crafties.cqrs.model.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/pets/new", method = RequestMethod.GET)
    public String getPetForm(final Model model) {
        model.addAttribute("owners", ownerService.findOwners());
        model.addAttribute("createPetCommand", new CreatePetCommand());
        return "pet";
    }

    @RequestMapping(value = "/pets/new", method = RequestMethod.POST)
    public String newPet(@Valid CreatePetCommand command, BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("createPetCommand", command);
            model.addAttribute("owners", ownerService.findOwners());
            return "pet";
        }
        petService.create(command.getName(), command.getType(), new OwnerId(command.getOwnerId()));
        return "redirect:/pets";
    }
}
