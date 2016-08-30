package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.OwnerId;

import java.util.List;

public interface PetService {
    List<Pet> findPets();

    Pet create(String name, PetType type, OwnerId ownerId);
}
