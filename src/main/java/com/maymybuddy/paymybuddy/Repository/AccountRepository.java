package com.maymybuddy.paymybuddy.Repository;

import com.maymybuddy.paymybuddy.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
@Repository
public class AccountRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public Account findDefaultAccount(int userId){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        return (Account) jdbcTemplate.queryForObject(DBConstants.FIND_ACCOUNT_DEFAULT_USER, params, new BeanPropertyRowMapper(Account.class));
    }

    public int save(Account account) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return jdbcTemplate.update(DBConstants.UPDATE_ACCOUNT_AMOUNT, new BeanPropertySqlParameterSource(account));
    }
}
