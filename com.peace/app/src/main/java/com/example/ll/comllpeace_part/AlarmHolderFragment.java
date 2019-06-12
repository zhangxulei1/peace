package com.example.ll.comllpeace_part;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public  class AlarmHolderFragment extends Fragment {
    /**
     * fragment
     */

        private static final String ARG_SECTION = "section";

        public AlarmHolderFragment() {
        }

        public static AlarmHolderFragment newInstance(String section) {
            AlarmHolderFragment fragment = new AlarmHolderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION, section);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.alarm_total_layout, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.tab_layout);
            textView.setText(getArguments().getString(ARG_SECTION));
            return rootView;
        }

    /**
     * pagerAdapter
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        String[] tabs = {"闹钟","小憩"};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return AlarmHolderFragment.newInstance(tabs[position]);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
