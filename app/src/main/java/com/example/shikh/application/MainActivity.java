package com.example.shikh.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Session session;
    ImageView btn_logout;
    ImageView imgView;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    int images[] = {R.drawable.lib1,R.drawable.lib2,R.drawable.lib3,R.drawable.lib4,
            R.drawable.lib5,R.drawable.lib6,R.drawable.lib7,R.drawable.lib8};
    String names[] = {"Life of Pi","Eclipse","Punishment","Ender's Game","Breaking Dawn","Kite Runner","1984","The Hobbit"};
    String authNames[] = {"Yann Martel","Stephenie Meyer","Fyodor Dostoevsky","Orson Scott Card","Stephenie Meyer","Khaled Hosseini","George Orwell","J. R. R. Tolkien"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        if (!session.loggedin()) {
            logout();
        }
        btn_logout = findViewById(R.id.btn_logout);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        imgView = findViewById(R.id.imgView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(images,names,authNames,this);
        mRecyclerView.setAdapter(mAdapter);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You are logged out!",Toast.LENGTH_SHORT).show();
                logout();
            }
        });
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent();
            i.setClass(MainActivity.this,CategoriesActivity.class);
            startActivity(i);
            }
        });
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
