package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<item> datas = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas.add(new item( "사용자 정보 입력", "개인정보 입력하기", R.drawable.person, R.drawable.person));
        datas.add(new item( "영상 확인",  "실시간으로 영상 확인하기" , R.drawable.cctv, R.drawable.cctv));
        datas.add(new item( "신고", "신고하기", R.drawable.siren, R.drawable.siren));

        recyclerView = findViewById(R.id.recycler);
        adapter= new MyAdapter(datas, this);
        recyclerView.setAdapter(adapter);

    }
}