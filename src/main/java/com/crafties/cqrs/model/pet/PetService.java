package com.crafties.cqrs.model.pet;

import java.util.List;

public interface PetService {
    List<Pet> findPets();
}
