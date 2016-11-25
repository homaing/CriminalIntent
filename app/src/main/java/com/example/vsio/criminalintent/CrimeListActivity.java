package com.example.vsio.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by vsio on 16-11-22.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
