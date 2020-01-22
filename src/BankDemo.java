import java.sql.*;

public class BankDemo {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/bank?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";

    public static void main(String[] args) throws ClassNotFoundException {
        printAllAccount();
        updateAmounts();
        printAllAccount();
    }

    private static void updateAmounts() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);
            statement.executeUpdate("update account set amount = 50 where id=1");
            statement.executeUpdate("update account set amount = 250 whee id=2");
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllAccount() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from account");

            while (resultSet.next()) {
                System.out.println("id" + resultSet.getInt(1));
                System.out.println("amount" + resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
