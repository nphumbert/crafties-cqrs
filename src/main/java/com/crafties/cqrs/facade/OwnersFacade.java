package com.crafties.cqrs.facade;

import java.util.List;

public interface OwnersFacade {
    List<OwnerDto> findOwners();
}
