package com.example.android.moviesapp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.moviesapp.Fragments.FavouriteMovies;
import com.example.android.moviesapp.Fragments.PopularMovies;
import com.example.android.moviesapp.Fragments.TopRated;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new PopularMovies();
            case 1:
                return new TopRated();
            case 2:
                return new FavouriteMovies();

            default:return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Popular";
            case 1 :
                return "Top Rated";
            case 2:

                return "Favourites";
            default:return " ";
        }

    }
}
