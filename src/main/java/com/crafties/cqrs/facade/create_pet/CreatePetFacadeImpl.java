package com.crafties.cqrs.facade.create_pet;

import com.crafties.cqrs.model.owner.OwnerId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CreatePetFacadeImpl implements CreatePetFacade {
    private final DataSource dataSource;

    public CreatePetFacadeImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<OwnerDto> findOwners() {
        return new JdbcTemplate(dataSource).query(
                "SELECT id, name FROM owner",
                (rs, rowNum) -> new OwnerDto(new OwnerId(rs.getLong("id")), rs.getString("name"))
        );
    }
}
