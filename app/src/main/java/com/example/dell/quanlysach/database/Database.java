package com.example.dell.quanlysach.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dell.quanlysach.get.HoaDonChiTietDao;
import com.example.dell.quanlysach.get.HoaDonDao;
import com.example.dell.quanlysach.get.NguoiDungDao;
import com.example.dell.quanlysach.get.SachDao;
import com.example.dell.quanlysach.get.TheLoaiDao;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int VESION=1;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDao.SQL_THE_LOAI);
        db.execSQL(SachDao.SQL_SACH);
        db.execSQL(HoaDonDao.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDao.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+NguoiDungDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+TheLoaiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+SachDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+HoaDonDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+HoaDonChiTietDao.TABLE_NAME);

        onCreate(db);

    }
}
