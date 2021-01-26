package com.skylar.beer_lifesaver.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.skylar.beer_lifesaver.BeerStyleDetailFragment;

import java.util.ArrayList;

import network.BeerStyle;

public class BeerStylePagerAdapter  extends FragmentPagerAdapter {

    ArrayList<BeerStyle> mBeerStyles;

    public BeerStylePagerAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment, ArrayList<BeerStyle> beerStyles) {
        super(fm);
        mBeerStyles = beerStyles;
    }

    @Override
        public CharSequence getPageTitle ( int position){
            return (CharSequence) mBeerStyles.get(position).getData();
        }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BeerStyleDetailFragment.newInstance(mBeerStyles.get(position));
    }

    @Override
    public int getCount() {
        return mBeerStyles.size();
    }
}