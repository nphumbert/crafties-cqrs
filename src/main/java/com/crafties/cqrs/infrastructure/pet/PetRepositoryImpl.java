package com.crafties.cqrs.infrastructure.pet;

import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PetRepositoryImpl implements PetRepository {

    private final DataSource dataSource;

    @Autowired
    public PetRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(Pet pet) {
        new JdbcTemplate(dataSource).update(
                "INSERT INTO pet (id, name, type, owner_id) VALUES (?, ?, ?, ?)",
                pet.getId().toString(), pet.getName(), pet.getType().name(), pet.getOwnerId() == null ? null : pet.getOwnerId().toString()
        );
    }

}
