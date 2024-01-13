package com.viplearner.url_shortener.backend;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface DatabaseBackend {
    @SqlQuery("select 1")
    Integer exampleQuery();
}
