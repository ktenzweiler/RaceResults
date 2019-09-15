package com.kodingwithkyle.raceresults.ui.main;

import com.kodingwithkyle.raceresults.models.Results;
import com.kodingwithkyle.raceresults.services.ResultsService;

import retrofit2.Callback;

public interface IViewModel {
    void init(IView view, ResultsService service, Callback<Results> callBack);

    void handleFetchResultsSuccess(Results body);

    void handleFetchResultsFailure(Results results);
}
