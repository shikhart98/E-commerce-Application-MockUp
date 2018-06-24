package com.example.shikh.application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.shikh.application.database.DatabaseHelper;
import com.example.shikh.application.database.table.BookTable;
import com.example.shikh.application.model.book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Session session;
    ImageView btn_logout;
    ImageView imgView;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences prefs = null;
    DatabaseHelper helper;
    SQLiteDatabase write;

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
        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);
        helper = new DatabaseHelper(this);
        write = helper.getWritableDatabase();

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

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            book b1 = new book("Life of Pi",
                    "Yann Martel",
                    125,
                    3,
                    "http://www.drtbooks.com/Upside%20Downside%20Up%20%20312x%20500.jpg",
                    "horror");

            book b2 = new book("Eclipse",
                    "Stephenie Meyer",
                    120,
                    4,
                    "http://www.drtbooks.com/Saysha%20%20312x%20500.jpg",
                    "motivation");

            book b3 = new book("Punishment",
                    "Stephenie Meyer",
                    255,
                    5,
                    "http://www.drtbooks.com/A%20Collection%20Of%20Life%20%20312x%20500.jpg",
                    "horror");

            book b4 = new book("Ender's Game",
                    "Orson Scott Card",
                    620,
                    4,
                    "http://www.drtbooks.com/The%20Awakening%20Of%20Joe%20Harper%20312%20x%20500.jpg",
                    "horror");

            book b5 = new book("Breaking Dawn",
                    "Fyodor Dostoevsky",
                    160,
                    3,
                    "https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/177568/DIG050061_1._SX312_QL80_TTD_.jpg",
                    "horror");

            book b6 = new book("Kite Runner",
                    "Yann Martel",
                    100,
                    3,
                    "http://www.drtbooks.com/Ear%20To%20The%20Ground%20%20312x%20500-1.jpg",
                    "horror");

            book b7 = new book("1984",
                    "Khaled Hosseini",
                    130,
                    3,
                    "https://bullcitypress.com/wp-content/uploads/2016/03/ThenWinter_500.jpg",
                    "motivation");

            book b8 = new book("The Hobbit",
                    "J. R. R. Tolkien",
                    600,
                    4,
                    "http://www.okthepk.ca/dataCprSiding/biblio/images/cp034.jpg",
                    "motivation");

            book b9 = new book("Albatraoz",
                    "Khaled Hosseini",
                    800,
                    5,
                    "http://hoffmanjade.com/books/Chu_Jade3.jpg",
                    "motivation");

            book b10 = new book("Kind World",
                    "J. R. R. Tolkien",
                    190,
                    3,
                    "https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/500403/500403._SX312_QL80_TTD_.jpg",
                    "motivation");

            book b11 = new book("Happy Living",
                    "Khaled Hosseini",
                    340,
                    2,
                    "http://www.drtbooks.com/My%20Friend%20Ms.%20Fran%20%20312x%20500-2.jpg",
                    "motivation");

            book b12 = new book("SuperMan",
                    "George Orwell",
                    599,
                    5,
                    "https://i.pinimg.com/originals/40/87/08/408708f905454b00eaf401e15974eb00.jpg",
                    "motivation");

            BookTable.insertBook(b1, write);
            BookTable.insertBook(b2, write);
            BookTable.insertBook(b3, write);
            BookTable.insertBook(b4, write);
            BookTable.insertBook(b5, write);
            BookTable.insertBook(b6, write);
            BookTable.insertBook(b7, write);
            BookTable.insertBook(b8, write);
            BookTable.insertBook(b9, write);
            BookTable.insertBook(b10, write);
            BookTable.insertBook(b11, write);
            BookTable.insertBook(b12, write);

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
