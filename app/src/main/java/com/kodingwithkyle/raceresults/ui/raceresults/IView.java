package com.kodingwithkyle.raceresults.ui.raceresults;

import com.kodingwithkyle.raceresults.models.Runner;

import java.util.List;

public interface IView {
    void updateNonPodiumRunners(List<Runner> runners);
}
