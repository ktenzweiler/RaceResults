package com.kodingwithkyle.raceresults.ui.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kodingwithkyle.raceresults.R;
import com.kodingwithkyle.raceresults.models.Results;
import com.kodingwithkyle.raceresults.models.Runner;
import com.kodingwithkyle.raceresults.ui.raceresults.RaceResultsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,
            R.string.tab_text_3};
    private final static int YOUTH_INDEX = 0;
    private final static int ADULT_INDEX = 1;
    private final static int SENIOR_INDEX = 2;
    private final static int YOUTH_AGE_LIMIT = 15;
    private final static int ADULT_AGE_LIMIT = 30;
    private final Context mContext;
    private ArrayList<Runner> youngRunners = new ArrayList<>();
    private ArrayList<Runner> adultRunners = new ArrayList<>();
    private ArrayList<Runner> seniorRunners = new ArrayList<>();

    public SectionsPagerAdapter(Context context, FragmentManager fm, Results results) {
        super(fm);
        mContext = context;
        Collections.sort(results.getRunners(), new Comparator<Runner>() {
            @Override
            public int compare(Runner runner, Runner t1) {
                return Integer.compare(runner.getTime(), t1.getTime());
            }
        });
        for (Runner runner : results.getRunners()) {
            if (runner.getAge() <= YOUTH_AGE_LIMIT) {
                youngRunners.add(runner);
            } else if (runner.getAge() < ADULT_AGE_LIMIT) {
                adultRunners.add(runner);
            } else {
                seniorRunners.add(runner);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == YOUTH_INDEX) {
            return RaceResultsFragment.newInstance(youngRunners);
        } else if (position == ADULT_INDEX) {
            return RaceResultsFragment.newInstance(adultRunners);
        } else if (position == SENIOR_INDEX) {
            return RaceResultsFragment.newInstance(seniorRunners);
        } else {
            return RaceResultsFragment.newInstance(youngRunners);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
