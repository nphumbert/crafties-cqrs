package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Pet> findPets() {
        return petRepository.findPets();
    }

    @Override
    public Pet create(String name, PetType type, OwnerId ownerId) {
        Owner owner = ownerRepository.find(ownerId);
        Pet pet = new Pet(PetId.generate(), name, type, owner);
        petRepository.save(pet);
        return pet;
    }
}
