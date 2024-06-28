package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner queryRunner = new QueryRunner();
    private SQLHelper() {
    }
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }
    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, codeSQL, new BeanHandler<>(DataHelper.VerificationCode.class));
    }
    @SneakyThrows
    public static void cleanDB() {
        var connect = getConn();
        queryRunner.execute(connect, "DELETE FROM auth_codes");
        queryRunner.execute(connect, "DELETE FROM card_transactions");
        queryRunner.execute(connect, "DELETE FROM cards");
        queryRunner.execute(connect, "DELETE FROM users");
    }

    @SneakyThrows
    public static void cleanAuthCodes() {
        var connect = getConn();
        queryRunner.execute(connect, "DELETE FROM auth_codes");
    }
    @SneakyThrows
    public static String getUserStatus() {
        var codeSQL = "SELECT status FROM users WHERE login = 'vasya'";
        var conn = getConn();
        return queryRunner.query(conn, codeSQL, new ScalarHandler<String>());
    }

}