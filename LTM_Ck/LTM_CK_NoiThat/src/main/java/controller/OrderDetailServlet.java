package controller;

import dao.OrderDetailDAO;
import model.OrderDetail;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class OrderDetailServlet extends HttpServlet {
    private OrderDetailDAO dao = new OrderDetailDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            List<OrderDetail> list = dao.getAll();
            req.setAttribute("orderDetails", list);
            req.getRequestDispatcher("/jsp/order_detail_list.jsp").forward(req, resp);

        } else if (action.equals("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            OrderDetail od = dao.getById(id);
            req.setAttribute("orderDetail", od);
            req.getRequestDispatcher("/jsp/order_detail_form.jsp").forward(req, resp);

        } else if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect("orderdetail");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("id") != null ? Integer.parseInt(req.getParameter("id")) : 0;
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));

        OrderDetail od = new OrderDetail(id, orderId, productId, quantity, price);

        if (id == 0) {
            dao.insert(od);
        } else {
            dao.update(od);
        }

        resp.sendRedirect("orderdetail");
    }
}
