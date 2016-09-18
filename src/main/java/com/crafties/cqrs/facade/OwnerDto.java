package com.crafties.cqrs.facade;

public class OwnerDto {

    private final String name;

    public OwnerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerDto ownerDto = (OwnerDto) o;

        return name.equals(ownerDto.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
