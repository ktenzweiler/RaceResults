package com.kodingwithkyle.raceresults.ui.raceresults;

import com.kodingwithkyle.raceresults.models.Runner;

import java.util.ArrayList;

public interface IViewModel {
    void init(IView view, ArrayList<Runner> runners);
}
