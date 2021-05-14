package com.example.dell.quanlysach.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dell.quanlysach.R;

import com.example.dell.quanlysach.model.SachBanChay;

import java.util.ArrayList;
import java.util.List;

public class SachBanChayActivity extends AppCompatActivity {
    private ListView listView;
    private List<SachBanChay> sachBanChayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.customlvsachbanchay);
        sachBanChayList = new ArrayList<>();


    }


}

