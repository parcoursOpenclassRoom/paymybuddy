package com.maymybuddy.paymybuddy.Repository;

public class DBConstants {
    public static final String FIND_USER_BY_USERNAME = "SELECT * FROM users WHERE email = :email";
    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = :id";
    public static final String FIND_USER_BY_CONNECTION = "SELECT u.id ,u.email," +
                                                        "p.first_name as 'profil.firstName',p.last_name as 'profil.lastName' " +
                                                        "FROM users as u inner join profil as p on u.profil_id = p.id inner join " +
                                                        "connection on u.id = connection.receiver_id WHERE status = true and connection.sender_id = " +
                                                        "(SELECT id FROM users WHERE email = :email)";
    public static final String FIND_ACCOUNT_DEFAULT_USER = "SELECT * FROM account WHERE user_id = :user_id AND default_account = true";
    public static final String UPDATE_ACCOUNT_AMOUNT = "update account SET balance = :balance, modified = NOW() WHERE id = :id ";
    public static final String INSERT_TRANSFER = "INSERT INTO transfer (amount, description, status, receiver_id, sender_id, created, modified) VALUES(:amount, :description, :status, :receiver_id, :sender_id, NOW(), NOW() )";
    public static final String INSERT_TRANSACTION = "INSERT INTO transaction (amount, description, type, account_id, created, modified) VALUES(:amount, :description, :type, :account_id, NOW(), NOW())";
    public static final String FIND_TRANSFER_USER_SENDER = "SELECT t.description, t.amount, CONCAT(p.first_name, ' ', p.last_name) as 'receiverName' FROM transfer as t inner join account as c on t.receiver_id = c.id inner join users as u on c.user_id = u.id inner join profil as p on p.id = u.profil_id WHERE t.sender_id = (SELECT a.id FROM account as a inner join users on a.user_id = users.id  WHERE users.email = :user_email)";
}
