package com.example.shikh.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shikh.application.Fragments.Crime;
import com.example.shikh.application.Fragments.Fiction;
import com.example.shikh.application.Fragments.Motivation;
import com.example.shikh.application.Fragments.horror;
import com.example.shikh.application.Fragments.romance;

/**
 * Created by shikh on 24-06-2018.
 */

public class PageAdapter extends FragmentPagerAdapter {

    int noOfTabs;

    public PageAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new horror();
            case 1:
                return new Motivation();
            case 2:
                return new Fiction();
            case 3:
                return new Crime();
            case 4:
               return new romance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
