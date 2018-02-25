package com.example.syyam.chatbot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Syyam on 08-Jul-17.
 */

class SectionPagerAdapter extends FragmentPagerAdapter{
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Chat chatFrament=new Chat();
                return chatFrament;

            case 1:
                Manual manualFragment=new Manual();
                return manualFragment;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 2; //cuz i have 2 tabs
    }

    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Virtual Advisor";
            case 1:
                return "Manage Account";
            default:
                return null;

        }

    }
}
