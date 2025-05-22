package controller;

import dao.OrderDAO;
import model.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            OrderDAO dao = new OrderDAO();
            if (action == null) action = "list";

            switch (action) {
                case "new":
                    request.getRequestDispatcher("jsp/order_form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Order o = dao.getOrderById(id);
                    request.setAttribute("order", o);
                    request.getRequestDispatcher("jsp/order_form.jsp").forward(request, response);
                    break;
                case "delete":
                    dao.deleteOrder(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("order");
                    break;
                default:
                    List<Order> list = dao.getAllOrders();
                    request.setAttribute("orderList", list);
                    request.getRequestDispatcher("jsp/order_list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            Order order = new Order();
            boolean isEdit = request.getParameter("id") != null && !request.getParameter("id").isEmpty();

            if (isEdit) order.setId(Integer.parseInt(request.getParameter("id")));
            order.setUserId(Integer.parseInt(request.getParameter("user_id")));
            order.setTotalPrice(Double.parseDouble(request.getParameter("total_price")));

            OrderDAO dao = new OrderDAO();
            if (isEdit) dao.updateOrder(order);
            else dao.addOrder(order);

            response.sendRedirect("order");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
