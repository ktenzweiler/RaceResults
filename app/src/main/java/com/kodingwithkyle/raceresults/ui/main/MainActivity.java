package com.kodingwithkyle.raceresults.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.kodingwithkyle.raceresults.R;
import com.kodingwithkyle.raceresults.databinding.ActivityMainBinding;
import com.kodingwithkyle.raceresults.models.Results;
import com.kodingwithkyle.raceresults.services.ResultsService;
import com.kodingwithkyle.raceresults.ui.adapters.SectionsPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IView {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final MainViewModel mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        Callback<Results> callBack = new Callback<Results>() {
            @Override
            public void onResponse(@NotNull Call<Results> call,
                                   @NotNull Response<Results> response) {
                if (response.isSuccessful()) {
                    mainViewModel.handleFetchResultsSuccess(response.body());
                } else {
                    mainViewModel.handleFetchResultsFailure(fetchLocalResults());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Results> call, @NotNull Throwable t) {
                mainViewModel.handleFetchResultsFailure(fetchLocalResults());
            }
        };
        mainViewModel.init(this, ResultsService.ServiceCreator.newService(), callBack);
    }

    @Override
    public void loadResults(Results results) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager(), results);
        ViewPager viewPager = binding.viewPager;
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

    private Results fetchLocalResults() {
        InputStream is;
        Results results = null;
        try {
            is = getApplication().getAssets().open("results.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);
            Gson gson = new Gson();
            results = gson.fromJson(json, Results.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}