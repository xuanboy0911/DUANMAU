package com.example.asm.SQLite.Helperr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String NAME = "library";
    private static final int VERSION = 1;

    public Dbhelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    private static final String IntoThuThu = "INSERT INTO ThuThu(userName,passWork,HoVaTen) VALUES" +
            "('admin','123','Hồ Nguyễn Thái Tú')," +
            "('huongle','123','Lê Thị Thu Hường')," +
            "('phuhm','123','Hồ Minh Phú')";
    private static final String IntoThanhVien = "INSERT INTO ThanhVien(maTV,hoTenTV,namSinh, soTK) VALUES" +
            "('1','Nguyen Van Teo','2003', '100029838')," +
            "('2','Nguyen Van Nam','1998', '100029838')," +
            "('3','Tran Van Thao','2001', '100029838')," +
            "('4','Le Thi Huyen','2008', '100029830')," +
            "('5','Tran Thi Luu','2003', '100029835')";
    private static final String IntoSach = "INSERT INTO Sach(maSach,tenSach,giaThue,soLuong,maLoaiSach) VALUES" +
            "('1','Java1','300000','10','1')," +
            "('2','Java2','300000','10','1')," +
            "('3','Java3','300000','10','1')," +
            "('4','FontEnd','300000','10','2')," +
            "('5','BackEnd','300000','10','2')," +
            "('6','JavaScript Cơ Bản','300000','10','3')," +
            "('7','JavaScript Nâng Cao','300000','10','3')," +
            "('8','React Native IOS','300000','10','4')," +
            "('9','React Native Web','300000','10','4')," +
            "('10','React Native Android','300000','10','4')," +
            "('11','Android Co Ban','300000','10','5')," +
            "('12','Android Nang Cao','300000','10','5')";
    private static final String IntoLoaiSach = "INSERT INTO LoaiSach(maLoaiSach,tenLoaiSach) VALUES" +
            "('1','Java')," +
            "('2','Web')," +
            "('3','JavaScript')," +
            "('4','React Native')," +
            "('5','Android')";
    private static final String IntoPhieuMuon = "INSERT INTO PhieuMuon(maPM,maTV,userName,maSach,ngayMuon,trangThai,tienThue) VALUES" +
            "( '1' , '1' , 'huongle' , '1' , '2022/10/01' , '1' , '300000' )," +
            "( '2' , '2' , 'huongle' , '2' , '2022/10/02' , '0' , '300000' )," +
            "( '3' , '3' , 'huongle' , '3' , '2022/10/03' , '1' , '300000' )," +
            "( '4' , '4' , 'huongle' , '4' , '2022/10/04' , '0' , '300000' )," +
            "( '5' , '5' , 'huongle' , '5' , '2022/10/05', '1' , '300000' )," +
            "( '6' , '1' , 'huongle' , '6' , '2022/10/06' , '0' , '300000' )," +
            "( '7' , '2' , 'huongle' , '7' , '2022/10/07' , '1' , '300000' )," +
            "( '8' , '3' , 'phuhm' , '8' ,'2022/10/08' , '0' , '300000' )," +
            "( '9' , '4' , 'phuhm' , '9' , '2022/10/09' , '1' , '300000' )," +
            "( '10' , '5' , 'phuhm' , '10' ,'2022/10/10' , '0' , '300000' )," +
            "( '11' , '1' , 'phuhm' , '11' ,'2022/10/11' , '1' , '300000' )," +
            "( '12' , '2' , 'phuhm' , '12' ,'2022/10/12' , '0' , '300000' )," +
            "( '13' , '3' , 'phuhm' , '1' ,'2022/10/13' , '1' , '300000' )," +
            "( '14' , '4' , 'phuhm' , '2' ,'2022/10/14' , '0' , '300000' )," +
            "( '15' , '5' , 'phuhm' , '3' ,'2022/10/15' , '1' , '300000' )";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String ThuThu = " CREATE TABLE ThuThu ( " +
                "userName text primary key not null ," +
                "passWork text not null," +
                "HoVaTen text not null" +
                ")";
        sqLiteDatabase.execSQL(ThuThu);

        String ThanhVien = " CREATE TABLE ThanhVien ( " +
                "maTV integer primary key autoincrement, " +
                "hoTenTV text not null, " +
                "namSinh text not null, " +
                "soTK integer not null " +
                ")";
        sqLiteDatabase.execSQL(ThanhVien);

        String LoaiSach = " CREATE TABLE LoaiSach ( " +
                "maLoaiSach integer primary key autoincrement," +
                "tenLoaiSach text not null " +
                ") ";
        sqLiteDatabase.execSQL(LoaiSach);

        String Sach = " CREATE TABLE Sach (" +
                "maSach integer primary key autoincrement," +
                "tenSach text not null," +
                "giaThue integer not null," +
                "soLuong integer not null," +
                "maLoaiSach integer not null," +
                " foreign key (maLoaiSach) REFERENCES LoaiSach (maLoaiSach) " +
                ") ";
        sqLiteDatabase.execSQL(Sach);

        String PhieuMuon = "CREATE TABLE PhieuMuon ( " +
                "maPM integer primary key autoincrement," +
                "maTV integer not null ," +
                "userName text not null," +
                "maSach integer not null," +
                "ngayMuon date not null," +
                "trangThai integer not null," +
                "tienThue integer not null," +
                "foreign key (maTV) REFERENCES ThanhVien (maTV), " +
                "foreign key (userName) REFERENCES ThuThu (userName)," +
                "foreign key (maSach) REFERENCES Sach (maSach)" +
                ")";
        sqLiteDatabase.execSQL(PhieuMuon);

        sqLiteDatabase.execSQL(IntoThuThu);
        sqLiteDatabase.execSQL(IntoSach);
        sqLiteDatabase.execSQL(IntoLoaiSach);
        sqLiteDatabase.execSQL(IntoThanhVien);
        sqLiteDatabase.execSQL(IntoPhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String ThuThu = " DROP TABLE IF EXISTS ThuThu";
        String ThanhVien = " DROP TABLE IF EXISTS ThanhVien";
        String LoaiSach = " DROP TABLE IF EXISTS LoaiSach";
        String Sach = " DROP TABLE IF EXISTS Sach";
        String PhieuMuon = " DROP TABLE IF EXISTS PhieuMuon";

        sqLiteDatabase.execSQL(ThuThu);
        sqLiteDatabase.execSQL(ThanhVien);
        sqLiteDatabase.execSQL(LoaiSach);
        sqLiteDatabase.execSQL(Sach);
        sqLiteDatabase.execSQL(PhieuMuon);

        onCreate(sqLiteDatabase);
    }
}
