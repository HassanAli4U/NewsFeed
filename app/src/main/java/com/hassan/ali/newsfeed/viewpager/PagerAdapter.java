package com.hassan.ali.newsfeed.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hassan.ali.newsfeed.ui.ArtsFragment;
import com.hassan.ali.newsfeed.ui.BusinessFragment;
import com.hassan.ali.newsfeed.ui.HealthFragment;
import com.hassan.ali.newsfeed.ui.HomeFragment;
import com.hassan.ali.newsfeed.ui.ScienceFragment;
import com.hassan.ali.newsfeed.ui.SportsFragment;
import com.hassan.ali.newsfeed.ui.TechnologyFragment;

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
                SportsFragment tab3 = new SportsFragment();
                return tab3;
            case 3:
                BusinessFragment tab4 = new BusinessFragment();
                return tab4;
            case 4:
                ScienceFragment tab5 =new ScienceFragment();

                return tab5;
            case 5:
                TechnologyFragment tab6=new TechnologyFragment();
                return tab6;
            case 6:
                HealthFragment tab7=new HealthFragment();
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
