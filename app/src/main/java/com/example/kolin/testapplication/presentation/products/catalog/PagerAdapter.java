package com.example.kolin.testapplication.presentation.products.catalog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kolin.testapplication.presentation.common.Updateable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 22.09.2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    private Bundle bundleToUpdate;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return (fragmentList.size() > position) ? fragmentList.get(position) : null;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof Updateable) {
            ((Updateable) object).update(bundleToUpdate);
        }
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, int position) {
        if (getItem(position) != null){
            fragmentList.remove(position);
            titleList.remove(position);
        }
        fragmentList.add(position, fragment);
        titleList.add(position, title);
        notifyDataSetChanged();
    }

    public void setTitleToPosition(String title, int position){
        titleList.remove(position);
        titleList.add(position, title);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void setBundleToUpdate(Bundle bundleToUpdate) {
        this.bundleToUpdate = bundleToUpdate;
    }
}
