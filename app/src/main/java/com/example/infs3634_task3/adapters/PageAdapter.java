package com.example.infs3634_task3.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.infs3634_task3.fragments.FavoriteFragment;
import com.example.infs3634_task3.fragments.SearchFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTab;

    public PageAdapter(@NonNull FragmentManager fm, int numOfTab) {

        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTab = numOfTab; }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 :
                return new SearchFragment();

            case 1 :
                return new FavoriteFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() { return numOfTab; }
}