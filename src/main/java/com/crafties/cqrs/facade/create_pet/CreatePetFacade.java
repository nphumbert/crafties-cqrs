package com.crafties.cqrs.facade.create_pet;

import java.util.List;

public interface CreatePetFacade {
    List<OwnerDto> findOwners();
}
