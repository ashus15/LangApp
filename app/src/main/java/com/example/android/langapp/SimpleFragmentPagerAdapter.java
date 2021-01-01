package com.example.android.langapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new ColorFragment();
        } else if(position==2){
            return new FamilyFragment();
        }else {
            return new PhraseFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
