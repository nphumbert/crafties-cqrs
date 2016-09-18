package com.crafties.cqrs.model.owner;

public interface OwnerRepository {
    Owner find(OwnerId ownerId);
}
