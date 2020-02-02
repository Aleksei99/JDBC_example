import java.sql.*;
import java.util.List;

public class UsersJdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException {

        //executeUpdate();
        //executeQuery();
        //executeU();
        //PreparedStatementQuery();
        //executeQ();

        UserDAO userDAO = new UserDAOImpl();
     //   userDAO.remove(10L);

        List<User> users = userDAO.findAllUsers();
        for (User user:users) {
            System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail());
        }

        User user = userDAO.findUser(1L);
        System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail());

       // User newUser = new User("justBot","JBt@gmail.com");
        //userDAO.save(newUser);
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
            System.out.println("rows Changed=" + changed);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeU() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "MySQLicui4cuL");
             Statement statement = connection.createStatement()) {

            String query = "INSERT INTO user (name, email) VALUES ('Genny3', 'Genny3@gmail.com')";
            if (statement.execute(query)) {     // т.к не возвращает ResultSet -> false
                int changed = statement.getUpdateCount();
                System.out.println("rows Changed=" + changed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeQ() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "MySQLicui4cuL");
             Statement statement = connection.createStatement()) {

            String query = "select * from user";
            if (statement.execute(query)) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        System.out.println("id| " + resultSet.getInt("iduser") + " " +
                                "|name| " + resultSet.getString("name") + " " +
                                "|email| " + resultSet.getString("email")
                        );
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void PreparedStatementQuery() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String query = "INSERT INTO user (name, email) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "MySQLicui4cuL");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "Vasia");
            preparedStatement.setString(2, "Vasia@gmail.com");
            preparedStatement.executeUpdate();
            int changed = preparedStatement.getUpdateCount();
            System.out.println("rows Changed=" + changed);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
