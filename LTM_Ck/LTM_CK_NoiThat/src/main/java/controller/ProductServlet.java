package controller;

import dao.ProductDAO;
import dao.CategoryDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        ProductDAO dao = new ProductDAO();

        try {
            switch (action) {
                case "new":
                    request.setAttribute("categories", new CategoryDAO().getAllCategories());
                    request.getRequestDispatcher("jsp/product_form.jsp").forward(request, response);
                    break;

                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Product product = dao.getProductById(id);
                    request.setAttribute("product", product);
                    request.setAttribute("categories", new CategoryDAO().getAllCategories());
                    request.getRequestDispatcher("jsp/product_form.jsp").forward(request, response);
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    dao.deleteProduct(deleteId);
                    response.sendRedirect("product");
                    break;

                case "listHome":
                    // Hiển thị danh sách sản phẩm cho trang người dùng (index.jsp)
                    List<Product> homeList = dao.getAllProducts();
                    request.setAttribute("productList", homeList);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;

                default:
                    // Danh sách sản phẩm quản trị (product_list.jsp)
                    List<Product> list = dao.getAllProducts();
                    request.setAttribute("productList", list);
                    request.getRequestDispatcher("jsp/product_list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Product product = new Product();
            boolean isEdit = request.getParameter("id") != null && !request.getParameter("id").isEmpty();

            if (isEdit) {
                product.setId(Integer.parseInt(request.getParameter("id")));
            }

            product.setName(request.getParameter("name"));
            product.setDescription(request.getParameter("description"));
            product.setPrice(Double.parseDouble(request.getParameter("price")));
            product.setImageUrl(request.getParameter("image_url"));
            product.setStock(Integer.parseInt(request.getParameter("stock")));
            product.setCategoryId(Integer.parseInt(request.getParameter("category_id")));

            ProductDAO dao = new ProductDAO();
            if (isEdit) {
                dao.updateProduct(product);
            } else {
                dao.addProduct(product);
            }

            response.sendRedirect("product");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi xử lý dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }
}
