package com.crafties.cqrs.web.owner;

import com.crafties.cqrs.model.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OwnersController {

    private final OwnerService ownerService;

    @Autowired
    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public String getOwners(final Model model) {
        model.addAttribute("owners", ownerService.findOwners());
        return "owners";
    }

}
