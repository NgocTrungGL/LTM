package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) action = "list";
            switch (action) {
                case "new":
                    request.getRequestDispatcher("jsp/user_form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    User user = new UserDAO().getUserById(id);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("jsp/user_form.jsp").forward(request, response);
                    break;
                case "delete":
                    new UserDAO().deleteUser(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("user");
                    break;
                default:
                    List<User> userList = new UserDAO().getAllUsers();
                    request.setAttribute("userList", userList);
                    request.getRequestDispatcher("jsp/user_list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = new User();
            boolean isEdit = request.getParameter("id") != null && !request.getParameter("id").isEmpty();

            if (isEdit) user.setId(Integer.parseInt(request.getParameter("id")));

            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setFullName(request.getParameter("full_name"));
            user.setEmail(request.getParameter("email"));

            UserDAO dao = new UserDAO();
            if (isEdit) dao.updateUser(user);
            else dao.addUser(user);

            response.sendRedirect("user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
