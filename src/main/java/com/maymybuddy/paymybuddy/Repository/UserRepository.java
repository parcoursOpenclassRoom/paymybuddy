package com.maymybuddy.paymybuddy.Repository;

import com.maymybuddy.paymybuddy.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;


@Repository
public class UserRepository extends JdbcDaoSupport {

    @Autowired private DataSource dataSource;
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public User findByEmail(String email){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        return (User) jdbcTemplate.queryForObject(DBConstants.FIND_USER_BY_USERNAME, params, new BeanPropertyRowMapper(User.class));
    }

    public User findById(int userId){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        return (User) jdbcTemplate.queryForObject(DBConstants.FIND_USER_BY_ID, params, new BeanPropertyRowMapper(User.class));
    }

    public List<User> findUserByConnection(String email){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        return jdbcTemplate.query(DBConstants.FIND_USER_BY_CONNECTION, params, new NestedRowMapper(User.class));
    }
}
