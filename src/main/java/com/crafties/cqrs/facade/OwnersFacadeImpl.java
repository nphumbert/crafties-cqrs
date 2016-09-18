package com.crafties.cqrs.facade;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OwnersFacadeImpl implements OwnersFacade {
    private final DataSource dataSource;

    public OwnersFacadeImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<OwnerDto> findOwners() {
        return new JdbcTemplate(dataSource).query("SELECT name FROM owner", (rs, rowNum) -> new OwnerDto(rs.getString("name")));
    }
}
