package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.OwnerId;

public interface PetService {
    Pet create(String name, PetType type, OwnerId ownerId);
}
