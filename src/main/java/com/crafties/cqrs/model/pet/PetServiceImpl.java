package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.OwnerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet create(String name, PetType type, OwnerId ownerId) {
        Pet pet = new Pet(PetId.generate(), name, type, ownerId);
        petRepository.save(pet);
        return pet;
    }
}
