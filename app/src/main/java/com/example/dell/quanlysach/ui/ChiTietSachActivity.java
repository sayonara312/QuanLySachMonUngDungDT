package com.example.dell.quanlysach.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.quanlysach.R;
import com.example.dell.quanlysach.get.SachDao;
import com.example.dell.quanlysach.get.TheLoaiDao;
import com.example.dell.quanlysach.model.Theloai;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSachActivity extends AppCompatActivity {
    SachDao sachDAO;
    TheLoaiDao theLoaiDAO;
    Spinner spnTheLoai;
    List<Theloai> listTheLoai = new ArrayList<>();
    //EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    String masach, tensach, nxb, tacgia, giabia, soluong;
    private EditText edMaSachThemSach;
    private EditText edTenSachThemSach;
    private EditText edTacGiaThemSach;
    private EditText edNXBThemSach;
    private EditText edGiaThemSach;
    private EditText edSoluongThemSach;
    //private Button btnThemThemSach;
    //private Button btnHuyThemSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chi_tiet_sach);
        initView();
        setTitle("Chi Tiết Sách");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sachDAO = new SachDao(ChiTietSachActivity.this);
        // load data
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        masach = b.getString("MASACH");
        //maTheLoai = b.getString("MATHELOAI");
        tensach = b.getString("TENSACH");
        nxb = b.getString("NXB");
        tacgia = b.getString("TACGIA");
        giabia = b.getString("GIABIA");
        soluong = b.getString("SOLUONG");
        edMaSachThemSach.setText(masach);
        edTenSachThemSach.setText(tensach);
        edNXBThemSach.setText(nxb);
        edTacGiaThemSach.setText(tacgia);
        edGiaThemSach.setText(giabia);
        edSoluongThemSach.setText(soluong);
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maTheLoai =
                        listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            /*edMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));*/
    }

    public void UpdateSach(View view) {
        if (sachDAO.updateSach(edMaSachThemSach.getText().toString(), maTheLoai,
                edTenSachThemSach.getText().toString(), edNXBThemSach.getText().toString(),
                edTacGiaThemSach.getText().toString(), String.valueOf(edGiaThemSach.getText().toString()),
                String.valueOf(edSoluongThemSach.getText().toString())) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(ChiTietSachActivity.this, SachActivity.class);
            startActivity(a);
        }
    }

    public void huyupdatesach(View view) {
        finish();
    }

    private void initView() {
        edMaSachThemSach = (EditText) findViewById(R.id.edMaSach_ThemSach);
        edTenSachThemSach = (EditText) findViewById(R.id.edTenSach_ThemSach);
        edTacGiaThemSach = (EditText) findViewById(R.id.edTacGia_ThemSach);
        edNXBThemSach = (EditText) findViewById(R.id.edNXB_ThemSach);
        edGiaThemSach = (EditText) findViewById(R.id.edGia_ThemSach);
        edSoluongThemSach = (EditText) findViewById(R.id.edSoluong_ThemSach);
        spnTheLoai = (Spinner) findViewById(R.id.spTheLoai_ThemSach);
        //btnThemThemSach = (Button) findViewById(R.id.btnThem_ThemSach);
        //btnHuyThemSach = (Button) findViewById(R.id.btnHuy_ThemSach);

    }
    public void getTheLoai(){
        theLoaiDAO = new TheLoaiDao(ChiTietSachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<Theloai> dataAdapter = new ArrayAdapter<Theloai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }
}
