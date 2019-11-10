package com.example.animalsapp;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.animalsapp.di.AppModule;
import com.example.animalsapp.di.DaggerViewModelComponent;
import com.example.animalsapp.model.AnimalApiService;
import com.example.animalsapp.utils.SharedPreferenceHelper;
import com.example.animalsapp.viewmodel.ListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {

    @Rule
    public InstantTaskExecutorRule mRule = new InstantTaskExecutorRule();

    @Mock
    AnimalApiService animalService;

    @Mock
    SharedPreferenceHelper prefs;

    Application mApplication = Mockito.mock(Application.class);

    ListViewModel mListViewModel = new ListViewModel(mApplication,true);


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        DaggerViewModelComponent.builder()
                .appModule(new AppModule(mApplication))
                .apiModule(new ApiModuleTest(animalService))
                .prefModule(new PrefsModuleTest(prefs))
                .build()
                .inject(mListViewModel);
    }

    @Before
    public void setupRxSchedulers(){

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> {
                    runnable.run();
                },true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }
}
