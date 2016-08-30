package com.crafties.cqrs.infrastructure.pet;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.pet.Pet;
import com.crafties.cqrs.model.pet.PetId;
import com.crafties.cqrs.model.pet.PetRepository;
import com.crafties.cqrs.model.pet.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository {

    private final DataSource dataSource;

    @Autowired
    public PetRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Pet> findPets() {
        return new JdbcTemplate(dataSource).query(
                "SELECT * FROM pet LEFT JOIN owner ON pet.owner_id = owner.id",
                (rs, rowNum) -> new Pet(
                        new PetId(rs.getLong("pet.id")),
                        rs.getString("pet.name"),
                        PetType.valueOf(rs.getString("pet.type")),
                        new Owner(new OwnerId(rs.getLong("owner.id")), rs.getString("owner.name"))
                )
        );
    }

    @Override
    public void save(Pet pet) {
        new JdbcTemplate(dataSource).update(
                "INSERT INTO pet (id, name, type, owner_id) VALUES (?, ?, ?, ?)",
                pet.id().toString(), pet.name(), pet.type().name(), pet.owner() == null ? null : pet.owner().id().toString()
        );
    }

}
