package iuh.fit.se.ongk.entity;



public class Khoa {
    private String maKhoa;
    private String tenKhoa;
    private String truongKhoa;
    private String moTa;


    public Khoa() {
    }

    public Khoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public Khoa(String maKhoa, String tenKhoa, String truongKhoa, String moTa) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.truongKhoa = truongKhoa;
        this.moTa = moTa;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getTruongKhoa() {
        return truongKhoa;
    }

    public void setTruongKhoa(String truongKhoa) {
        this.truongKhoa = truongKhoa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
