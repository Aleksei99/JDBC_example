import java.util.List;

public interface UserDAO {
    List<User> findAllUsers() throws ClassNotFoundException;
    User findUser(Long id) throws ClassNotFoundException;
    void save(User user) throws ClassNotFoundException;
    void remove(Long id) throws ClassNotFoundException;
}
