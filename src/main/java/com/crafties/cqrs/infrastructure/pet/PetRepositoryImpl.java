package com.crafties.cqrs.infrastructure.pet;

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
                "SELECT * FROM pet",
                (rs, rowNum) -> new Pet(new PetId(rs.getLong("id")), rs.getString("name"), PetType.valueOf(rs.getString("type")))
        );
    }
}
