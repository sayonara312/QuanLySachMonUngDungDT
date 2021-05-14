package com.example.dell.quanlysach.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.quanlysach.R;
import com.example.dell.quanlysach.get.NguoiDungDao;
import com.example.dell.quanlysach.model.NguoiDung;

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edpass, edRepass,edtusername_changepass;
    NguoiDungDao nguoiDungDao;
    Button btnchangePass;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Đổi Mật Khẩu");
        edpass = (EditText) findViewById(R.id.edtnewPassword);
        edRepass = (EditText) findViewById(R.id.edtnewPassword2);
        btnchangePass = (Button) findViewById(R.id.btnsavepass);
        edtusername_changepass =(EditText) findViewById(R.id.edtusername_changepass);
//
//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("key");
//        final String username=bundle.getString("username1");

        btnchangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(edpass.getText().toString().matches("")){
//                    edpass.setError("Nhập password");
//                }
//                if(edRepass.getText().toString().matches("")){
//                    edRepass.setError("Nhập password mới");
//                }
//                NguoiDung user1=nguoiDungDao.getUser(username);
//                String password1=user1.getPassword();
//
//                NguoiDung user=new NguoiDung();
//                user.setPassword(edRepass.getText().toString());
//                if(password1.matches(edpass.getText().toString().trim())) {
//                    nguoiDungDao.changePassword(username, user);
//                    Intent intent3=new Intent(getApplicationContext(),NguoidungActivity.class);
//                    startActivity(intent3);
//                }else {
//                    edpass.setError("Password chưa chính xác");
//                }
                /*if (nguoiDungDao.updateInfoNguoiDung(username,edPhone.getText().toString(), edFullName.getText().toString())>0){
                    Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();

                    Intent a = new Intent(ChiTietNguoiDungActivity.this,NguoidungActivity.class);
                    startActivity(a);
                }*/
                Intent intent = getIntent();
                Bundle b= intent.getExtras();
                username=b.getString("USERNAME");
                password=b.getString("PASSWORD");
                edpass.setText(password);
                edtusername_changepass.setText(username);
                //SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                //String strUserName = pref.getString("USERNAME", "");
                nguoiDungDao = new NguoiDungDao(DoiMatKhauActivity.this);
                //NguoiDung user = new NguoiDung((username, edpass.getText().toString())>0);
                try {
                    if (validateForm() > 0) {
                        if (nguoiDungDao.updatePassNguoiDung(username,edpass.getText().toString()) > 0) {
                            Toast.makeText(getApplicationContext(), "Lưu thành công",
                                    Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(DoiMatKhauActivity.this, NguoidungActivity.class);
                            startActivity(a);
                        } else {
                            Toast.makeText(getApplicationContext(), "Lưu thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });

    }

    public int validateForm() {
        int check = 1;
        if (edpass.getText().length() == 0 || edRepass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edpass.getText().toString();
            String rePass = edRepass.getText().toString();

            if (pass.length() < 6) {
                edpass.setError(getString(R.string.notify_length_pass));
                check = -1;
            }
            if (!pass.equals(rePass)) {
                edRepass.setError("Mật khẩu không trùng");
                check = -1;
            }
        }
        return check;
    }


    public void CancelChangePass(View view) {
        finish();
    }
}
