package com.viplearner.url_shortener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viplearner.url_shortener.backend.DatabaseBackend;
import com.viplearner.url_shortener.backend.DatabaseConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.immutables.value.Value;
import org.jdbi.v3.core.Jdbi;

/**
 * Server configuration for the url-shortener API server.
 */
@Value.Immutable
@JsonDeserialize(as = ImmutableUrlShortenerConfiguration.class)
public abstract class UrlShortenerConfiguration extends Configuration {

    @JsonProperty("swagger")
    abstract SwaggerBundleConfiguration getSwaggerBundleConfiguration();

    @JsonProperty("database")
    abstract DatabaseConfiguration getDatabaseConfiguration();

    public Jdbi createDBI(Environment environment, String name) {
        final JdbiFactory factory = new JdbiFactory();
        return factory.build(environment, getDatabaseConfiguration().getDataSourceFactory(), name);
    }

    public DatabaseBackend getDatabaseBackend(Environment environment) {
        return createDBI(environment, "jdbi-backend").onDemand(DatabaseBackend.class);
    }
}
