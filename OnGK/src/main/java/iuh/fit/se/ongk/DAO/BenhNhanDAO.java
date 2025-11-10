package iuh.fit.se.ongk.DAO;

import iuh.fit.se.ongk.entity.BenhNhan;

import java.util.List;

public interface BenhNhanDAO {
    List<BenhNhan> getAllBenhNhan();
    List<BenhNhan> getFindByName(String hoTen);

    List<BenhNhan> getDSBNKhoa(String maKhoa);

    boolean addBenhNhan(BenhNhan benhNhan);
}
