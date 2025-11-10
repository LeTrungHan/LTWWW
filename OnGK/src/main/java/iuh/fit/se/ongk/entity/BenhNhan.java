package iuh.fit.se.ongk.entity;


import java.util.Date;

public class BenhNhan {
    private String maBN;
    private String hoTen;
    private Date ngayNhapVien;
    private String chuanDoan;

    private Khoa khoa;

    public BenhNhan() {
    }

    public BenhNhan(String maBN, String hoTen, Date ngayNhapVien, String chuanDoan, Khoa khoa) {
        this.maBN = maBN;
        this.hoTen = hoTen;
        this.ngayNhapVien = ngayNhapVien;
        this.chuanDoan = chuanDoan;
        this.khoa = khoa;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgayNhapVien() {
        return ngayNhapVien;
    }

    public void setNgayNhapVien(Date ngayNhapVien) {
        this.ngayNhapVien = ngayNhapVien;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }
}



