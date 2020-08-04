package com.maymybuddy.paymybuddy.Repository;

import com.maymybuddy.paymybuddy.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
@Repository
public class TransactionRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public int save(Transaction transaction) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("amount", transaction.getAmount());
        params.addValue("description", transaction.getDescription());
        params.addValue("type", transaction.getType());
        params.addValue("account_id", transaction.getAccount().getId());
        return jdbcTemplate.update(DBConstants.INSERT_TRANSACTION, params);
    }
}
