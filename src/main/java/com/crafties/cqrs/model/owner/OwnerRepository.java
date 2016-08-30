package com.crafties.cqrs.model.owner;

import java.util.List;

public interface OwnerRepository {
    List<Owner> findOwners();

    Owner find(OwnerId ownerId);
}
