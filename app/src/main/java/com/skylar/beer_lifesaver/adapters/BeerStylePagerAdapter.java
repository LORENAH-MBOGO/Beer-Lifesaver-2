package com.skylar.beer_lifesaver.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import network.BeerStyle;

public class BeerStylePagerAdapter  extends FragmentPagerAdapter {

    private ArrayList<BeerStyle> mBeerStyles;

    public BeerStylePagerAdapter(@NonNull FragmentManager fm, ArrayList<BeerStyle> behavior) {
        super(fm);
    }

    @Override
        public CharSequence getPageTitle ( int position){
            return mBeerStyles.get(position).getMessage();
        }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}