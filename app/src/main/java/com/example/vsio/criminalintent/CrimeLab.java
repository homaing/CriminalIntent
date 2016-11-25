package com.example.vsio.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by vsio on 16-11-22.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes;

    public static CrimeLab getCrimeLab() {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    private CrimeLab() {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID uuid) {
        for (Crime crime : mCrimes
                ) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }
}
