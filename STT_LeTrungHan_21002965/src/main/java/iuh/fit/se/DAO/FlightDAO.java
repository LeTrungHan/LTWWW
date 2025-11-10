package iuh.fit.se.DAO;

import iuh.fit.se.entity.ChuyenBay;

import java.util.List;

public interface FlightDAO {
    List<ChuyenBay> getAllFight();

    boolean addChuyenBay(ChuyenBay chuyenBay);
    boolean deleteChuyenBay(int id);
}
