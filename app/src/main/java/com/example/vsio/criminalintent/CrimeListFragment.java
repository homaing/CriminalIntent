package com.example.vsio.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vsio on 16-11-22.
 */

public class CrimeListFragment extends Fragment {
    private List<Crime> mCrimes;
    private RecyclerView mRecyclerView;
    private CrimeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (adapter == null) {
            adapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mCrimeTitle;
        private TextView mCrimeDate;
        private CheckBox mCrimeSolved;
        private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mCrimeTitle = (TextView) itemView.findViewById(R.id.list_item_crime_title_list_view);
            mCrimeDate = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mCrimeSolved = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mCrimeTitle.setText(crime.getTitle());
            mCrimeDate.setText(crime.getDate().toString());
            mCrimeSolved.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(getActivity(), mCrime.getTitle() + "Toast", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getActivity(), CrimeActivity.class);
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
