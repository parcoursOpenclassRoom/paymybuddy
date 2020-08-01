package com.maymybuddy.paymybuddy.Repository;

import com.maymybuddy.paymybuddy.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Repository
public class UserRepository extends JdbcDaoSupport {

    @Autowired private DataSource dataSource;
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public User findByEmail(User user){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return (User) jdbcTemplate.queryForObject(DBConstants.FIND_USER_BY_USERNAME, new BeanPropertySqlParameterSource(user), new BeanPropertyRowMapper(User.class));
    }
}
