package com.maymybuddy.paymybuddy.Repository;

import com.maymybuddy.paymybuddy.Entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class TransferRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public int save(Transfer transfer) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("amount", transfer.getAmount());
        params.addValue("description", transfer.getDescription());
        params.addValue("status", transfer.isStatus());
        params.addValue("receiver_id", transfer.getReceiver().getId());
        params.addValue("sender_id", transfer.getSender().getId());
        return jdbcTemplate.update(DBConstants.INSERT_TRANSFER, params);
    }

    public List<Transfer> findByUserSender(String userEmail){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_email", userEmail);
        return jdbcTemplate.query(DBConstants.FIND_TRANSFER_USER_SENDER, params, new NestedRowMapper(Transfer.class));
    }
}
