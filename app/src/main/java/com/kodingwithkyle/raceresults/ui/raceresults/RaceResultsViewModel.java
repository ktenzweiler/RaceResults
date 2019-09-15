package com.kodingwithkyle.raceresults.ui.raceresults;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.kodingwithkyle.raceresults.models.Runner;

import java.util.ArrayList;

public class RaceResultsViewModel extends ViewModel implements IViewModel {

    public ObservableField<String> bronzeName = new ObservableField<>("n/a");
    public ObservableField<String> bronzeTime = new ObservableField<>("n/a");
    public ObservableField<String> bronzeAge = new ObservableField<>("n/a");
    public ObservableField<String> silverName = new ObservableField<>("n/a");
    public ObservableField<String> silverTime = new ObservableField<>("n/a");
    public ObservableField<String> silverAge = new ObservableField<>("n/a");
    public ObservableField<String> goldName = new ObservableField<>("n/a");
    public ObservableField<String> goldTime = new ObservableField<>("n/a");
    public ObservableField<String> goldAge = new ObservableField<>("n/a");

    @Override
    public void init(IView view, ArrayList<Runner> runners) {
        for (int i = 0; i < runners.size(); i++) {
            if (i == 0) {
                goldName.set(runners.get(i).getName());
                goldAge.set(runners.get(i).getAge() + " yrs old");
                goldTime.set(runners.get(i).getTime() + " minutes");
            } else if (i == 1) {
                silverName.set(runners.get(i).getName());
                silverAge.set(runners.get(i).getAge() + " yrs old");
                silverTime.set(runners.get(i).getTime() + " minutes");
            } else if (i == 2) {
                bronzeName.set(runners.get(i).getName());
                bronzeAge.set(runners.get(i).getAge() + " yrs old");
                bronzeTime.set(runners.get(i).getTime() + " minutes");
            }
        }

        if (runners.size() > 3) {
            view.updateNonPodiumRunners(runners.subList(3, runners.size()));
        }
    }
}