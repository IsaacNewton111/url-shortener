package com.viplearner.url_shortener.backend;

import com.viplearner.url_shortener.dto.UrlObject;
import com.viplearner.url_shortener.mapper.UrlMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


@RegisterRowMapper(UrlMapper.class)
public interface DatabaseBackend {
    @SqlQuery("select 1")
    Integer exampleQuery();

    @SqlUpdate("insert into url (url, short_url, time_last_updated) values (:url, :shortUrl, :timeLastUpdated)")
    void insertUrl(@BindBean UrlObject urlObject);

    @SqlQuery("select * from url where short_url = :shortUrl")
    UrlObject findByShortUrl(@Bind("shortUrl") String shortUrl);
}
