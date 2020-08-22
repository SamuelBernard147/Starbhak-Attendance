package com.samuelbernard.starbhakattendance.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragmentList = new ArrayList<>(); //    Arraylist fragment & title
    private ArrayList<String> mFragmentTitle = new ArrayList<>();

    /**
     * Indicates that only the current fragment will be
     * in the Lifecycle.State#RESUMED state. All other Fragments
     * are capped at Lifecycle.State#STARTED.
     */
    public TabFragmentAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void removeitem(int position) {
        mFragmentList.remove(position);
        mFragmentTitle.remove(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    /*
     * Adding fragment
     * @param fragment, title
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitle.add(title);
    }
}