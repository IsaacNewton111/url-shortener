package com.viplearner.url_shortener.mapper;

import com.viplearner.url_shortener.dto.UrlObject;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UrlMapper implements RowMapper<UrlObject> {
    @Override
    public UrlObject map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new UrlObject(rs.getString("url"), rs.getString("short_url"), rs.getLong("time_last_updated"));
    }
}