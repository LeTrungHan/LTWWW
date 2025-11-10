package iuh.fit.se.Controller;

import iuh.fit.se.DAO.impl.FlightDAOImpl;
import iuh.fit.se.entity.ChuyenBay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/FlightServlet")

public class FlightServlet extends HttpServlet {
    private FlightDAOImpl dao = new FlightDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "delete":
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteChuyenBay(id);
                resp.sendRedirect("FlightServlet?action=list");
                break;
            case "add":
                req.getRequestDispatcher("WEB-INF/views/add-flight.jsp").forward(req, resp);
                break;
            default:
                List<ChuyenBay> flights = dao.getAllFight();
                req.setAttribute("flights", flights);
                req.getRequestDispatcher("WEB-INF/views/list-flights.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String airplaneName = req.getParameter("airplaneName");
        String departureAirport = req.getParameter("departureAirport");
        String arrivalAirport = req.getParameter("arrivalAirport");
        Date departureDate = Date.valueOf(req.getParameter("departureDate"));
        Date arrivalDate = Date.valueOf(req.getParameter("arrivalDate"));
        double seatFare = Double.parseDouble(req.getParameter("seatFare"));
        int flightStatus = Integer.parseInt(req.getParameter("flightStatus"));

        ChuyenBay f = new ChuyenBay(0, airplaneName, departureAirport, arrivalAirport, departureDate, arrivalDate, seatFare, flightStatus);
        dao.addChuyenBay(f);
        resp.sendRedirect("FlightServlet?action=list");
    }
}
