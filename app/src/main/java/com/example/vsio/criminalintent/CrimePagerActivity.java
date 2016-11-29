package com.example.vsio.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by vsio on 16-11-29.
 */

public class CrimePagerActivity extends FragmentActivity {
    private static final String EXTRA_CRIME_ID = "com.example.vsio.criminalintent.crime_id";

    private ArrayList<Crime> mCrimes;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mCrimes = CrimeLab.get(this).getCrimes();

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public Fragment getItem(int position) {
                UUID crimeId = mCrimes.get(position).getId();
                return CrimeFragment.newInstance(crimeId);
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


        Serializable crimeId = getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID uuid) {

        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, uuid);
        return intent;
    }

}
