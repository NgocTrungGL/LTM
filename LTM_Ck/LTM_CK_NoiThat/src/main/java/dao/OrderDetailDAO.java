package dao;

import model.OrderDetail;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    public List<OrderDetail> getAll() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM order_details";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderDetail od = new OrderDetail(
                    rs.getInt("id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );
                list.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insert(OrderDetail od) {
        String sql = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(OrderDetail od) {
        String sql = "UPDATE order_details SET order_id=?, product_id=?, quantity=?, price=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getPrice());
            ps.setInt(5, od.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM order_details WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderDetail getById(int id) {
        String sql = "SELECT * FROM order_details WHERE id=?";
        OrderDetail od = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                od = new OrderDetail(
                    rs.getInt("id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return od;
    }
}
