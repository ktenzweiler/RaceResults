package com.kodingwithkyle.raceresults.ui.raceresults;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kodingwithkyle.raceresults.R;
import com.kodingwithkyle.raceresults.databinding.FragmentRaceResultsBinding;
import com.kodingwithkyle.raceresults.models.Runner;
import com.kodingwithkyle.raceresults.ui.adapters.NonPodiumRunnersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Race Results Fragment.
 */
public class RaceResultsFragment extends Fragment implements IView {

    private RaceResultsViewModel raceResultsViewModel;
    private FragmentRaceResultsBinding binding;

    public static RaceResultsFragment newInstance(@NonNull ArrayList<Runner> runners) {
        RaceResultsFragment fragment = new RaceResultsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Runner.class.getName(), runners);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        raceResultsViewModel = ViewModelProviders.of(this).get(RaceResultsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_race_results, container, false);
        binding.setViewModel(raceResultsViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        raceResultsViewModel.init(this,
                getArguments().<Runner>getParcelableArrayList(Runner.class.getName()));
    }

    @Override
    public void updateNonPodiumRunners(List<Runner> runners) {
        NonPodiumRunnersAdapter adapter = new NonPodiumRunnersAdapter(getContext(),
                R.layout.list_item_runner, R.id.name, runners);
        binding.nonPodiumRunners.setAdapter(adapter);
    }
}