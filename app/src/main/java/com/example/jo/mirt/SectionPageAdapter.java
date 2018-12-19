package com.example.jo.mirt;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jo on 3/23/2018.
 */

public class SectionPageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mfragmentTitleList = new ArrayList<>();

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return  mfragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mfragmentList.add(fragment);
        mfragmentTitleList.add(title);
    }


}
