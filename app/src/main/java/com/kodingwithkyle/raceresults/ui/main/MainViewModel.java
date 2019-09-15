package com.kodingwithkyle.raceresults.ui.main;

import androidx.lifecycle.ViewModel;

import com.kodingwithkyle.raceresults.models.Results;
import com.kodingwithkyle.raceresults.services.ResultsService;

import retrofit2.Call;
import retrofit2.Callback;

public class MainViewModel extends ViewModel implements IViewModel {

    private IView mView;

    @Override
    public void init(IView view, ResultsService service, Callback<Results> callBack) {
        mView = view;
        Call<Results> resultsCall = service.fetchResults();
        resultsCall.enqueue(callBack);
    }

    @Override
    public void handleFetchResultsSuccess(Results body) {
        mView.loadResults(body);
    }

    @Override
    public void handleFetchResultsFailure(Results results) {
        mView.loadResults(results);
    }
}
