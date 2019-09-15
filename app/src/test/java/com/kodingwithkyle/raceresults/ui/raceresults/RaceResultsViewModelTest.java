package com.kodingwithkyle.raceresults.ui.raceresults;

import com.kodingwithkyle.raceresults.models.Runner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class RaceResultsViewModelTest {

    @Mock
    private IView mView;

    private RaceResultsViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mViewModel = new RaceResultsViewModel();
    }

    @Test
    public void init() {
        ArrayList<Runner> runners = new ArrayList<>();
        Runner goldRunner = new Runner("gold", 1, 14);
        Runner silverRunner = new Runner("silver", 2, 14);
        Runner bronzeRunner = new Runner("bronze", 3, 14);
        Runner fourthPlaceRunner = new Runner("fourth", 4, 14);
        Runner fifthPlace = new Runner("fifth", 5, 14);
        runners.add(goldRunner);
        runners.add(silverRunner);
        runners.add(bronzeRunner);
        runners.add(fourthPlaceRunner);
        runners.add(fifthPlace);

        mViewModel.init(mView, runners);
        assertEquals(mViewModel.goldName.get(), goldRunner.getName());
        assertEquals(mViewModel.goldAge.get(), goldRunner.getAge() + " yrs old");
        assertEquals(mViewModel.goldTime.get(), goldRunner.getTime() + " minutes");

        assertEquals(mViewModel.silverName.get(), silverRunner.getName());
        assertEquals(mViewModel.silverAge.get(), silverRunner.getAge() + " yrs old");
        assertEquals(mViewModel.silverTime.get(), silverRunner.getTime() + " minutes");

        assertEquals(mViewModel.bronzeName.get(), bronzeRunner.getName());
        assertEquals(mViewModel.bronzeAge.get(), bronzeRunner.getAge() + " yrs old");
        assertEquals(mViewModel.bronzeTime.get(), bronzeRunner.getTime() + " minutes");

        verify(mView).updateNonPodiumRunners(runners.subList(3, runners.size()));
    }
}