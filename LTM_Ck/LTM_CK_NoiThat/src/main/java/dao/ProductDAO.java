package dao;

import model.Product;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO() throws SQLException {
        conn = DBUtil.getConnection();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getDouble("price"));
            p.setImageUrl(rs.getString("image_url"));
            p.setStock(rs.getInt("stock"));
            p.setCategoryId(rs.getInt("category_id"));
            list.add(p);
        }
        return list;
    }

    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getDouble("price"));
            p.setImageUrl(rs.getString("image_url"));
            p.setStock(rs.getInt("stock"));
            p.setCategoryId(rs.getInt("category_id"));
            return p;
        }
        return null;
    }

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, image_url, stock, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setDouble(3, product.getPrice());
        stmt.setString(4, product.getImageUrl());
        stmt.setInt(5, product.getStock());
        stmt.setInt(6, product.getCategoryId());
        stmt.executeUpdate();
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name=?, description=?, price=?, image_url=?, stock=?, category_id=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setDouble(3, product.getPrice());
        stmt.setString(4, product.getImageUrl());
        stmt.setInt(5, product.getStock());
        stmt.setInt(6, product.getCategoryId());
        stmt.setInt(7, product.getId());
        stmt.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
