package com.example.asm.SQLite.model;

public class ThanhVien {
    private  int maTV, tienTV;
    private  String hoTenTV, namSinh;


    public ThanhVien(int maTV, String hoTenTV, String namSinh, int tienTV) {
        this.maTV = maTV;
        this.hoTenTV = hoTenTV;
        this.namSinh = namSinh;
        this.tienTV = tienTV;
    }

    public ThanhVien() {
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTenTV() {
        return hoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        this.hoTenTV = hoTenTV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public int getTienTV(){
        return tienTV;
    }

    public void setTienTV(int tienTV){
        this.tienTV = tienTV;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTenTV='" + hoTenTV + '\'' +
                ", namSinh='" + namSinh + '\'' +
                ", tienTV='" + tienTV + '\'' +
                '}';
    }
}
