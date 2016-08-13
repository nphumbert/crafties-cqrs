package com.crafties.cqrs.model.pet;

import java.util.List;

public interface PetRepository {
    List<Pet> findPets();
}
