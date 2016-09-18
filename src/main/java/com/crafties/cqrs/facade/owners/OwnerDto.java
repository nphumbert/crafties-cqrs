package com.crafties.cqrs.facade.owners;

public class OwnerDto {

    private final String name;
    private final Long numberOfPets;

    public OwnerDto(String name, Long numberOfPets) {
        this.name = name;
        this.numberOfPets = numberOfPets;
    }

    public String getName() {
        return name;
    }

    public Long getNumberOfPets() {
        return numberOfPets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerDto ownerDto = (OwnerDto) o;

        if (!name.equals(ownerDto.name)) return false;
        return numberOfPets.equals(ownerDto.numberOfPets);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + numberOfPets.hashCode();
        return result;
    }
}
