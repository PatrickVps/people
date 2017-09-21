package com.people.trombinoscope.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by patrickvongpraseuth on 21/09/2017.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, List fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
