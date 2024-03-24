package com.viplearner.url_shortener;

import com.viplearner.url_shortener.backend.DatabaseBackend;
import com.viplearner.url_shortener.resource.AccessShortUrlResource;
import com.viplearner.url_shortener.resource.GetShortUrlResource;
import com.viplearner.url_shortener.resource.TestResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Main entry point to the url-shortener API server.
 */
public final class UrlShortenerApplication extends Application<UrlShortenerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new UrlShortenerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<UrlShortenerConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<UrlShortenerConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(UrlShortenerConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(final UrlShortenerConfiguration configuration, final Environment environment) {
        System.out.printf("Loaded configuration: %s%n", configuration);

        DatabaseBackend databaseBackend = configuration.getDatabaseBackend(environment);

        environment.jersey().register(new TestResource());
        environment.jersey().register(new GetShortUrlResource(databaseBackend));
        environment.jersey().register(new AccessShortUrlResource(databaseBackend));
    }
}
