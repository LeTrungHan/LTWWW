package iuh.fit.se.ongk.controller;

import iuh.fit.se.ongk.DAO.KhoaDAO;
import iuh.fit.se.ongk.DAO.impl.KhoaDAOImpl;
import iuh.fit.se.ongk.entity.Khoa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/khoa")
public class KhoaServlet extends HttpServlet {
    private KhoaDAO khoaDAO = new KhoaDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Khoa> list = khoaDAO.getAllKhoa();
        request.setAttribute("khoaList", list);
        request.getRequestDispatcher("/WEB-INF/views/danh-sach-khoa.jsp").forward(request, response);
    }

}
