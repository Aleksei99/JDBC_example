import java.sql.*;
import java.util.concurrent.Callable;

public class Procedures {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/eshop?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String query = "call proc(?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, 5);
            callableStatement.execute();
            try (ResultSet resultSet = callableStatement.getResultSet()) {
                while (resultSet.next()) {
                    System.out.println("id| " + resultSet.getInt("iduser") + " " +
                            "|name| " + resultSet.getString("name") + " " +
                            "|email| " + resultSet.getString("email")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
//            callableStatement.registerOutParameter(1,Types.INTEGER);
//            callableStatement.execute();
//            System.out.println(callableStatement.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
