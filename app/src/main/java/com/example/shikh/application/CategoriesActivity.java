package com.example.shikh.application;

import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.shikh.application.Fragments.Crime;
import com.example.shikh.application.Fragments.Fiction;
import com.example.shikh.application.Fragments.Motivation;
import com.example.shikh.application.Fragments.horror;
import com.example.shikh.application.Fragments.romance;

public class CategoriesActivity extends AppCompatActivity implements Crime.OnFragmentInteractionListener, romance.OnFragmentInteractionListener, Fiction.OnFragmentInteractionListener, horror.OnFragmentInteractionListener, Motivation.OnFragmentInteractionListener {

    TabLayout tabLayout;
    TabItem tab_horror;
    TabItem tab_motivation;
    TabItem tab_fiction;
    TabItem tab_crime;
    TabItem tab_romance;
    Toolbar toolbar;
    ViewPager viewPager;
    ImageView backnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        tabLayout = findViewById(R.id.tablayout);
        tab_horror = findViewById(R.id.tab_horror);
        tab_motivation = findViewById(R.id.tab_motivation);
        tab_fiction = findViewById(R.id.tab_fiction);
        tab_crime = findViewById(R.id.tab_crime);
        tab_romance = findViewById(R.id.tab_romance);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.pager);
        backnav = findViewById(R.id.backNav);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this,
                    R.color.horror));
        }

        backnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PagerAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.horror));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.horror));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this,
                                R.color.horror));
                    }
                } else if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.motivation));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.motivation));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this,
                                R.color.motivation));
                    }
                } else if (tab.getPosition() == 3) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.fiction));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.fiction));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this,
                                R.color.fiction));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.crime));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.crime));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this,
                                R.color.crime));
                    }
                } else if (tab.getPosition() == 4) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.romance));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(CategoriesActivity.this, R.color.romance));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this,
                                R.color.romance));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
