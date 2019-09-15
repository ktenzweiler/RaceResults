package com.kodingwithkyle.raceresults.ui.main;

import android.app.Application;

import com.kodingwithkyle.raceresults.models.Results;
import com.kodingwithkyle.raceresults.models.Runner;
import com.kodingwithkyle.raceresults.services.ResultsService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainViewModelTest {

    @Mock
    private IView mView;

    @Mock
    private ResultsService mService;

    @Mock
    private Call<Results> mCall;

    @Mock
    private Callback<Results> mCallback;

    @Mock
    private Application application;

    private MainViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mViewModel = new MainViewModel();
        when(mService.fetchResults())
                .thenReturn(mCall);
        mViewModel.init(mView, mService, mCallback);
    }

    @Test
    public void init() {
        verify(mCall).enqueue(mCallback);
    }

    @Test
    public void handleFetchResultsSuccess() {
        Results results = new Results("Race Results", new ArrayList<Runner>());
        mViewModel.handleFetchResultsSuccess(results);
        verify(mView).loadResults(results);
    }

    @Test
    public void handleFetchResultsFailure() {
        Results results = new Results("Race Results", new ArrayList<Runner>());
        mViewModel.handleFetchResultsFailure(results);
        verify(mView).loadResults(results);
    }
}