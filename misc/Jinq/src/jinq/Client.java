package jinq;

import util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;

public class Client {
    public static void main(String[] args) throws IOException, SQLException {
        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            try(Statement statement = conn.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM unidb_lecturers")) {
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                        System.out.println(rs.getString(2));
                        System.out.println(rs.getString(3));
                        System.out.println(rs.getString(4));
                    }
                }
            }
        }
    }
}
