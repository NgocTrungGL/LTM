package dao;

import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() throws SQLException {
        conn = DBUtil.getConnection();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            list.add(user);
        }
        return list;
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
        return null;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password, full_name, email) VALUES(?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getFullName());
        stmt.setString(4, user.getEmail());
        stmt.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username=?, password=?, full_name=?, email=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getFullName());
        stmt.setString(4, user.getEmail());
        stmt.setInt(5, user.getId());
        stmt.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
