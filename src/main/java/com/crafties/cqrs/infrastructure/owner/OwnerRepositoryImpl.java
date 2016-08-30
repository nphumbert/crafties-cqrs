package com.crafties.cqrs.infrastructure.owner;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {

    private final DataSource datasource;

    public OwnerRepositoryImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Owner> findOwners() {
        return new JdbcTemplate(datasource).query("SELECT * FROM owner", new OwnerRowMapper());
    }

    @Override
    public Owner find(OwnerId ownerId) {
        return new JdbcTemplate(datasource).queryForObject("SELECT * FROM owner WHERE id = " + ownerId, new OwnerRowMapper());
    }

    private static class OwnerRowMapper implements RowMapper<Owner> {
        @Override
        public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Owner(new OwnerId(rs.getLong("id")), rs.getString("name"));
        }
    }
}
