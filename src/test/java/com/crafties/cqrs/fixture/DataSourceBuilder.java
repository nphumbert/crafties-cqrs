package com.crafties.cqrs.fixture;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class DataSourceBuilder {


    private String dataScript;

    private DataSourceBuilder() {

    }

    public static DataSourceBuilder aDataSource() {
        return new DataSourceBuilder();
    }

    public DataSourceBuilder withDataScript(String dataScript) {
        this.dataScript = dataScript;
        return this;
    }

    public DataSource build() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder()
                .addScript("db/create-db.sql")
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true);

        if (dataScript != null) {
            databaseBuilder.addScript(dataScript);
        }

        return databaseBuilder.build();
    }

}
