import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/eshop?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";

    @Override
    public List<User> findAllUsers() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery("select * from user")) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("iduser"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUser(Long id) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        User user = new User();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where iduser = ?")){
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()){
                        user.setId(resultSet.getInt("iduser"));
                        user.setName(resultSet.getString("name"));
                        user.setEmail(resultSet.getString("email"));
                    }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user (name, email) values (?,?)")){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) throws ClassNotFoundException {

        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("delete from user where iduser = ?")){
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
