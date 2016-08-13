package com.crafties.cqrs.infrastructure.owner;

import com.crafties.cqrs.model.owner.Owner;
import com.crafties.cqrs.model.owner.OwnerId;
import com.crafties.cqrs.model.owner.OwnerRepository;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerRepositoryImplTest {

    private DataSource datasource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("db/create-db.sql")
                .addScript("owner/insert-owners.sql")
                .build();
    }

    @Test
    public void should_find_owners() {
        // given
        OwnerRepository ownerRepository = new OwnerRepositoryImpl(datasource());

        // when
        List<Owner> owners = ownerRepository.findOwners();

        // then
        assertThat(owners).containsExactly(new Owner(new OwnerId(1L), "Robert"), new Owner(new OwnerId(2L), "Laure"));
    }

}