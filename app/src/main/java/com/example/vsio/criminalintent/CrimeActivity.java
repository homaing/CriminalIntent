package com.example.vsio.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {


    private static final String EXTRA_CRIME_ID = "com.example.vsio.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {
//        return new CrimeFragment();
        Intent intent = getIntent();
        UUID crimeId = (UUID) intent.getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID uuid) {

        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, uuid);
        return intent;
    }
}
