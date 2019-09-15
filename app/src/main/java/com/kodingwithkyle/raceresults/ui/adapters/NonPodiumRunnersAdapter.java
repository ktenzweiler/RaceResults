package com.kodingwithkyle.raceresults.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kodingwithkyle.raceresults.R;
import com.kodingwithkyle.raceresults.models.Runner;

import java.util.List;

public class NonPodiumRunnersAdapter extends ArrayAdapter {

    public NonPodiumRunnersAdapter(Context context, int resource, int textViewResourceId,
                                   List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        TextView nameTV, timeTV, ageTV, rankTV;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_runner, null);
        }
        nameTV = v.findViewById(R.id.name);
        timeTV = v.findViewById(R.id.time);
        ageTV = v.findViewById(R.id.age);
        rankTV = v.findViewById(R.id.rank);
        Runner runner = (Runner) getItem(position);
        nameTV.setText(runner.getName());
        timeTV.setText(String.valueOf(runner.getTime()));
        ageTV.setText(String.valueOf(runner.getAge()));
        rankTV.setText(String.valueOf(position + 4));
        return v;
    }
}
