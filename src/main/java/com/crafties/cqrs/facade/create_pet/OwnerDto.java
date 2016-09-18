package com.crafties.cqrs.facade.create_pet;

import com.crafties.cqrs.model.owner.OwnerId;

public class OwnerDto {

    private final OwnerId id;
    private final String name;

    public OwnerDto(OwnerId id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerDto ownerDto = (OwnerDto) o;

        if (!id.equals(ownerDto.id)) return false;
        return name.equals(ownerDto.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
