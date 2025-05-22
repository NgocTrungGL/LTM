package dao;

import model.Category;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection conn;

    public CategoryDAO() throws SQLException {
        conn = DBUtil.getConnection();
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            list.add(c);
        }
        return list;
    }

    public Category getCategoryById(int id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            return c;
        }
        return null;
    }

    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categories(name) VALUES(?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, category.getName());
        stmt.executeUpdate();
    }

    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE categories SET name=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, category.getName());
        stmt.setInt(2, category.getId());
        stmt.executeUpdate();
    }

    public void deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
