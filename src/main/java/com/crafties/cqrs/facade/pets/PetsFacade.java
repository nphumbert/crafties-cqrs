package com.crafties.cqrs.facade.pets;

import java.util.List;

public interface PetsFacade {

    List<PetDto> findPets();

}
