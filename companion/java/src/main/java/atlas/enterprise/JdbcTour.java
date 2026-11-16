package atlas.enterprise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcTour {
    private JdbcTour() {
    }

    public static void transfer(Connection connection, long from, long to, int amount)
            throws SQLException {
        boolean previousAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (PreparedStatement withdraw = connection.prepareStatement(
                "update account set balance = balance - ? where id = ?");
             PreparedStatement deposit = connection.prepareStatement(
                "update account set balance = balance + ? where id = ?")) {

            withdraw.setInt(1, amount);
            withdraw.setLong(2, from);
            if (withdraw.executeUpdate() != 1) {
                throw new SQLException("source account not found");
            }

            deposit.setInt(1, amount);
            deposit.setLong(2, to);
            if (deposit.executeUpdate() != 1) {
                throw new SQLException("target account not found");
            }

            connection.commit();
        } catch (SQLException error) {
            connection.rollback();
            throw error;
        } finally {
            connection.setAutoCommit(previousAutoCommit);
        }
    }

    public static void printAccounts(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet rows = statement.executeQuery(
                 "select id, name, balance from account order by id")) {
            while (rows.next()) {
                System.out.printf("%d | %s | %d%n",
                    rows.getLong("id"),
                    rows.getString("name"),
                    rows.getInt("balance"));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("usage: java atlas.enterprise.JdbcTour <jdbc-url>");
            return;
        }
        try (Connection connection = DriverManager.getConnection(args[0])) {
            printAccounts(connection);
        }
    }
}
