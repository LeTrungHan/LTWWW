package iuh.fit.se.ongk.DAO.impl;

import iuh.fit.se.ongk.DAO.BenhNhanDAO;
import iuh.fit.se.ongk.entity.BenhNhan;
import iuh.fit.se.ongk.entity.Khoa;
import iuh.fit.se.ongk.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BenhNhanDAOImpl implements BenhNhanDAO {
    @Override
    public List<BenhNhan> getAllBenhNhan() {
        List<BenhNhan>  bn =  new ArrayList<BenhNhan>();
        try(Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from benhnhan");
        ) {
            while (res.next()){
                BenhNhan benhNhan = new BenhNhan();
                benhNhan.setMaBN(res.getString("MABN"));
                benhNhan.setHoTen(res.getString("HOTEN"));
                benhNhan.setNgayNhapVien(res.getTime("NGAYNHAPVIEN"));
                benhNhan.setChuanDoan(res.getString("CHUANDOAN"));
                benhNhan.setKhoa(new Khoa(res.getString("MAKHOA")));
                bn.add(benhNhan);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bn;
    }

    @Override
    public List<BenhNhan> getFindByName(String hoTen) {
        List<BenhNhan>  bn =  new ArrayList<BenhNhan>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pre = connection.prepareStatement("select * from benhnhan where HOTEN like ?");
        ) {
            pre.setString(1, "%"+hoTen+"%");
            ResultSet res = pre.executeQuery();
            while (res.next()){
                BenhNhan benhNhan = new BenhNhan();
                benhNhan.setMaBN(res.getString("MABN"));
                benhNhan.setHoTen(res.getString("HOTEN"));
                benhNhan.setNgayNhapVien(res.getTime("NGAYNHAPVIEN"));
                benhNhan.setChuanDoan(res.getString("CHUANDOAN"));
                benhNhan.setKhoa(new Khoa(res.getString("MAKHOA")));
                bn.add(benhNhan);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bn;
    }

    @Override
    public List<BenhNhan> getDSBNKhoa(String maKhoa) {
        List<BenhNhan>  bn =  new ArrayList<BenhNhan>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pre = connection.prepareStatement("select * from benhnhan where MAKHOA like ?");
        ) {
            pre.setString(1, maKhoa);
            ResultSet res = pre.executeQuery();
            while (res.next()){
                BenhNhan benhNhan = new BenhNhan();
                benhNhan.setMaBN(res.getString("MABN"));
                benhNhan.setHoTen(res.getString("HOTEN"));
                benhNhan.setNgayNhapVien(res.getTime("NGAYNHAPVIEN"));
                benhNhan.setChuanDoan(res.getString("CHUANDOAN"));
                benhNhan.setKhoa(new Khoa(res.getString("MAKHOA")));
                bn.add(benhNhan);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bn;
    }

    @Override
    public boolean addBenhNhan(BenhNhan benhNhan) {
        String sql = "INSERT INTO BENHNHAN (HOTEN, NGAYNHAPVIEN, CHUANDOAN, MAKHOA) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setString(1, benhNhan.getHoTen());
            ps.setDate(2, new Date(benhNhan.getNgayNhapVien().getTime()));
            ps.setString(3, benhNhan.getChuanDoan());
            ps.setString(4, benhNhan.getKhoa().getMaKhoa());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }
