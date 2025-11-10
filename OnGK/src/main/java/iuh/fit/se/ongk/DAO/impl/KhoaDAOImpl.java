package iuh.fit.se.ongk.DAO.impl;

import iuh.fit.se.ongk.DAO.KhoaDAO;
import iuh.fit.se.ongk.entity.Khoa;
import iuh.fit.se.ongk.utils.DBConnection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KhoaDAOImpl implements KhoaDAO {

    @Override
    public List<Khoa> getAllKhoa() {
        List<Khoa> khoas = new ArrayList<Khoa>();
        try(Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from khoa");
            ) {
            while (res.next()){
                Khoa khoa = new Khoa();
                khoa.setMaKhoa(res.getString("MAKHOA"));
                khoa.setTenKhoa(res.getString("TENKHOA"));
                khoa.setTruongKhoa(res.getString("TRUONGKHOA"));
                khoa.setMoTa(res.getString("MOTA"));
                khoas.add(khoa);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return khoas;
    }

}
