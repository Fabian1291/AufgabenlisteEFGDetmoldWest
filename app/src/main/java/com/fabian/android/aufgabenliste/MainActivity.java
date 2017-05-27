package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled (false);

        toolbar.setSubtitle(R.string.Untertitel);

        viewPager = (ViewPager) findViewById (R.id.viewPager);
        setupViewPager (viewPager);

        tabLayout = (TabLayout) findViewById (R.id.tabs);
        tabLayout.setupWithViewPager (viewPager);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_version:
                Intent intent = new Intent (this, Version.class);
                startActivity (intent);
                return true;
            case R.id.action_changelog:
                Intent intent1 = new Intent (this, Changelog.class);
                startActivity (intent1);
                return true;
        }

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainmenu, menu);

        return true;
    }

    private void setupViewPager (ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter (getSupportFragmentManager());
        adapter.addFragment (new FragmentOne(), "Aufgaben");
        adapter.addFragment (new FragmentTwo(), "Erledigt");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String > mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter (FragmentManager manager) {super(manager);}

        @Override
        public Fragment getItem (int position) {return mFragmentList.get (position);}

        @Override
        public int getCount () {return mFragmentList.size();}

        public void addFragment (Fragment fragment, String title)
        {
            mFragmentList.add (fragment);
            mFragmentTitleList.add (title);
        }

        @Override
        public CharSequence getPageTitle (int position)
        {
            return mFragmentTitleList.get (position);
        }
    }
}
