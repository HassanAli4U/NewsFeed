package com.hassan.ali.newsfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                ArtsFragment tab2 = new ArtsFragment();
                return tab2;
            case 2:
                FashionFragment tab3 = new FashionFragment();
                return tab3;
            case 3:
                SportsFragment tab4 = new SportsFragment();
                return tab4;
            case 4:
                MoviesFragment tab5 = new MoviesFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
