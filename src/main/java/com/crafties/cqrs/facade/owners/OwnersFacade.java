package com.crafties.cqrs.facade.owners;

import java.util.List;

public interface OwnersFacade {
    List<OwnerDto> findOwners();
}
