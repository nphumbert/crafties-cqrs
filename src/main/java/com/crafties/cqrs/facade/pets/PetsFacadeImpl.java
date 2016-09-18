package com.crafties.cqrs.facade.pets;

import com.crafties.cqrs.model.pet.PetType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PetsFacadeImpl implements PetsFacade {
    private DataSource dataSource;

    public PetsFacadeImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<PetDto> findPets() {
        return new JdbcTemplate(dataSource).query(
                "SELECT * FROM pet LEFT JOIN owner ON pet.owner_id = owner.id",
                (rs, rowNum) -> new PetDto(
                        rs.getString("name"),
                        PetType.valueOf(rs.getString("type")),
                        rs.getString("owner.name")
                )
        );
    }
}
