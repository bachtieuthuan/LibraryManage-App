package App.Users;

import App.database.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDao {

    public User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection connection = DatabaseConnect.getConnection()){
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("uid"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getDate("ngaydk").toLocalDate(),
                        resultSet.getString("loaitk"),
                        resultSet.getInt("status")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer register(String username, String password, String loaitk) {
        if(isUserAlready(username)){
            return -1;
        } else {
            String query = "INSERT INTO users (USERNAME, PASSWORD, LOAITK) VALUES (?,?,?)";
            try (Connection connection = DatabaseConnect.getConnection()){
                assert connection != null;
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, loaitk);
                statement.executeUpdate();
                return 1;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public void updateStatus(int UID, int STATUS) {
        String query = "UPDATE users SET status = ? WHERE UID = ?";
        try (Connection connection = DatabaseConnect.getConnection()){
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, STATUS);
            statement.setInt(2, UID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Boolean isUserAlready(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseConnect.getConnection()){
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}