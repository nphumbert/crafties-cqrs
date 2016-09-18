package com.crafties.cqrs.model.owner;

public class Owner {

    private final OwnerId id;
    private final String name;

    public Owner(OwnerId id, String name) {
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

        Owner owner = (Owner) o;

        return id.equals(owner.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
