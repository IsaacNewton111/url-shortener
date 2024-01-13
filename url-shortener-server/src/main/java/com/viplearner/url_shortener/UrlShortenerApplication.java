package com.viplearner.url_shortener;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.viplearner.url_shortener.backend.DatabaseBackend;
import com.viplearner.url_shortener.resource.TestResource;

/**
 * Main entry point to the url-shortener API server.
 */
public final class UrlShortenerApplication extends Application<UrlShortenerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new UrlShortenerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<UrlShortenerConfiguration> bootstrap) {

    }

    @Override
    public void run(final UrlShortenerConfiguration configuration, final Environment environment) {
        DatabaseBackend databaseBackend = configuration.getDatabaseBackend(environment);

        environment.jersey().register(new TestResource());
    }
}
