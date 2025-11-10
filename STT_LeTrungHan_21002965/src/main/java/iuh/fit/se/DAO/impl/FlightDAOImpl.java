package iuh.fit.se.DAO.impl;

import iuh.fit.se.DAO.FlightDAO;
import iuh.fit.se.utils.DBConnection;
import iuh.fit.se.entity.ChuyenBay;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {

    @Override
    public List<ChuyenBay> getAllFight() {
        List<ChuyenBay> cb = new ArrayList<>();
        try(
                Connection connection = DBConnection.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs =stmt.executeQuery("select * from flights ORDER BY departure_date DESC");
                ){
            while (rs.next()){
                ChuyenBay fl = new ChuyenBay();
                rs.getInt("id");
                rs.getString("airplane_name");
                rs.getString("departure_date");
                rs.getString("arrival_airport");
                rs.getDate("departure_date");
                rs.getDate("arrival_date");
                rs.getDouble("seat_fare");
                rs.getInt("flight_status");
                cb.add(fl);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cb;
    }

    @Override
    public boolean addChuyenBay(ChuyenBay chuyenBay) {

        String sql="INSERT INTO flights(airplane_name, departure_airport, arrival_airport, departure_date, arrival_date, seat_fare, flight_status) VALUES (?,?,?,?,?,?,?)";
        try(
                Connection cn = DBConnection.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
        ){
            ps.setString(1, chuyenBay.getAirplane_name());
            ps.setString(2, chuyenBay.getDeparture_airport());
            ps.setString(3, chuyenBay.getArrival_airport());
            ps.setDate(4, new Date(chuyenBay.getDeparture_date().getTime()));
            ps.setDate(5, new Date(chuyenBay.getArrival_date().getTime()));
            ps.setDouble(6, chuyenBay.getSeat_fare());
            ps.setInt(7, chuyenBay.getFlight_status());

            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteChuyenBay(int id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
