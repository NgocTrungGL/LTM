package controller;

import dao.CategoryDAO;
import model.Category;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) action = "list";
            CategoryDAO dao = new CategoryDAO();

            switch (action) {
                case "new":
                    request.getRequestDispatcher("jsp/category_form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Category c = dao.getCategoryById(id);
                    request.setAttribute("category", c);
                    request.getRequestDispatcher("jsp/category_form.jsp").forward(request, response);
                    break;
                case "delete":
                    dao.deleteCategory(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("category");
                    break;
                default:
                    List<Category> list = dao.getAllCategories();
                    request.setAttribute("categoryList", list);
                    request.getRequestDispatcher("jsp/category_list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Category category = new Category();
            boolean isEdit = request.getParameter("id") != null && !request.getParameter("id").isEmpty();

            if (isEdit) category.setId(Integer.parseInt(request.getParameter("id")));

            category.setName(request.getParameter("name"));

            CategoryDAO dao = new CategoryDAO();
            if (isEdit) dao.updateCategory(category);
            else dao.addCategory(category);

            response.sendRedirect("category");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
