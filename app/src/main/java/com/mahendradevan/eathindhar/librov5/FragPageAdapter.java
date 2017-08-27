package com.mahendradevan.eathindhar.librov5;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Eathindhar on 27-08-2017.
 */

public class FragPageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> Pages = new ArrayList<>();

    public FragPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return Pages.get(position);
    }

    @Override
    public int getCount() {
        return Pages.size();
    }

    public void addPager(Fragment frag){
        Pages.add(frag);
    }
}
