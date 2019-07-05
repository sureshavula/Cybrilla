package com.cybrilla.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Dao {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dao.class);

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setBizDataSource(DataSource jdbc) {
        this.jdbcTemplate = new JdbcTemplate(jdbc);

    }

    public void saveShortUrl(String shortUrl,String longUrl) {
        String SQL = "INSERT INTO cybrilla (long_url,short_url) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, longUrl);
                        preparedStatement.setString(2, shortUrl);
                        return preparedStatement;
                    }
                }, keyHolder);
    }

    public String getLongUrl(String shortUrl){
        String longUrl = null;
        String sql ="SELECT long_url FROM cybrilla WHERE shortUrl = ?";
        longUrl = jdbcTemplate.queryForObject(sql, new Object[]{shortUrl},String.class);
        return longUrl;
    }

}
