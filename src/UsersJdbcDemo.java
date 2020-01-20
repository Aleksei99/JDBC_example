import java.sql.*;

public class UsersJdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException {

        executeUpdate();
        executeQuery();

    }

    private static void executeQuery() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "MySQLicui4cuL");
             Statement statement = connection.createStatement()) {

            String query = "select * from user";

            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    System.out.println("id| " + resultSet.getInt("iduser") + " " +
                            "|name| " + resultSet.getString("name") + " " +
                            "|email| " + resultSet.getString("email")
                    );
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeUpdate() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "MySQLicui4cuL");
             Statement statement = connection.createStatement()) {

            String query = "INSERT INTO user (name, email) VALUES ('Genny', 'Genny@gmail.com')";
            int changed = statement.executeUpdate(query);
            System.out.println("rows Changed="+changed);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
