package dao;

import model.Order;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection conn;

    public OrderDAO() throws SQLException {
        conn = DBUtil.getConnection();
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setUserId(rs.getInt("user_id"));
            o.setOrderDate(rs.getTimestamp("order_date"));
            o.setTotalPrice(rs.getDouble("total_price"));
            orders.add(o);
        }

        return orders;
    }

    public Order getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setUserId(rs.getInt("user_id"));
            o.setOrderDate(rs.getTimestamp("order_date"));
            o.setTotalPrice(rs.getDouble("total_price"));
            return o;
        }
        return null;
    }

    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders(user_id, total_price) VALUES(?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, order.getUserId());
        stmt.setDouble(2, order.getTotalPrice());
        stmt.executeUpdate();
    }

    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET user_id=?, total_price=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, order.getUserId());
        stmt.setDouble(2, order.getTotalPrice());
        stmt.setInt(3, order.getId());
        stmt.executeUpdate();
    }

    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
