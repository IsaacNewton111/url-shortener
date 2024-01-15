package com.viplearner.url_shortener.dto;

import com.viplearner.url_shortener.mapper.UrlMapper;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

@RegisterRowMapper(UrlMapper.class)
public class UrlObject {
    private final String url;
    private final String shortUrl;
    private final long timeLastUpdated;
    public UrlObject(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.timeLastUpdated = System.currentTimeMillis();
    }

    public UrlObject(String url, String shortUrl, long timeLastUpdated) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.timeLastUpdated = timeLastUpdated;
    }

    public String getUrl() {
        return url;
    }
    public String getShortUrl() {
        return shortUrl;
    }

    public long getTimeLastUpdated() {
        return timeLastUpdated;
    }

}
